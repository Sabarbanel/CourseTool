package com.example.sarah.coursetool.Database;

import com.example.sarah.coursetool.Course.CourseInterface;
import com.example.sarah.coursetool.Course.ScheduledCourse;
import com.example.sarah.coursetool.UserProfile.Profile;
import com.example.sarah.coursetool.UserProfile.StudentProfile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Semaphore;

/**
 * Class that implements all database methods. The methods will be called through proxy classes
 */
public class RealDatabase implements LoginDatebaseInterface, UserDatabase {
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private DataSnapshot snapshot;
    private Profile userProfile;

    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Constructs a RealDatabase that will update when remote data changes
     */
    public RealDatabase() {
        database = FirebaseDatabase.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();

        // Create Semaphore that will be used to block till snapshot is initialized
        final Semaphore semaphore = new Semaphore(0);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                snapshot = dataSnapshot;

                // tell the caller that we're done
                semaphore.release();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // failed to read new data

                // tell the caller that we're done
                semaphore.release();
            }
        });

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public UserDatabase getProfileDatabase(String userName, String password) throws InvalidParameterException {
        // Values required to create the profile
        String name;
        Date birthday;
        ArrayList<CourseInterface> enrolledCourses = new ArrayList<>();

        // key = courseID, value = grade
        HashMap<Integer, Integer> grades = new HashMap<>();

        // Check Credentials
        DataSnapshot profileSnapshot;
        try {
            profileSnapshot = snapshot.child("Profile").child(userName);
        } catch (NullPointerException ex) {
            throw new InvalidParameterException("Username not found. ");
        }
        String databasePass = profileSnapshot.child("Password").getValue().toString();
        if (!databasePass.equals(password))
            throw new InvalidParameterException("Incorrect Password");


        // Acquire profile values
        name = profileSnapshot.child("Name").getValue().toString();

        birthday = new Date();
        try {
            birthday = dateFormatter.parse(profileSnapshot.child("Birthday").getValue().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (DataSnapshot courseID: profileSnapshot.child("Courses").child("SchedEntries").getChildren()) {
            // get Scheduled course info
            int id, room;
            String prof, deptCode, desc;
            ArrayList<Date> startTimes, endTimes;
            ArrayList<Integer> preReqs;

            DataSnapshot scheduleSnapshot = snapshot.child("Schedule").child(courseID.getValue().toString());
            id = Integer.parseInt(scheduleSnapshot.getKey().toString());
            room = Integer.parseInt(scheduleSnapshot.child("Room").getValue().toString());
            prof = scheduleSnapshot.child("Prof").getValue().toString();
            deptCode = snapshot.child("Course").child(scheduleSnapshot.child("CourseNum").getValue().toString()).child("DeptCode").getValue().toString();
            desc = snapshot.child("Course").child(scheduleSnapshot.child("CourseNum").getValue().toString()).child("Description").getValue().toString();

            startTimes = new ArrayList<>();
            for (DataSnapshot startTime : courseID.child("StartTimes").getChildren()) {
                try {
                    startTimes.add(dateFormatter.parse(startTime.getValue().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            endTimes = new ArrayList<>();
            for (DataSnapshot endTime : courseID.child("EndTimes").getChildren()) {
                try {
                    endTimes.add(dateFormatter.parse(endTime.getValue().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            preReqs = new ArrayList<>();
            for (DataSnapshot prereq: snapshot.child("Course").child(scheduleSnapshot.child("CourseNum").getValue().toString()).child("Prerequsites").getChildren()) {
                preReqs.add(Integer.parseInt(prereq.getValue().toString()));
            }

            enrolledCourses.add(new ScheduledCourse(id, room, prof, deptCode, desc,
                    startTimes, endTimes, preReqs));

            grades.put(Integer.parseInt(courseID.getValue().toString()), Integer.parseInt(profileSnapshot.child("Courses").child("Mark").child(courseID.getKey().toString()).getValue().toString()));
        }



        // Create the profile
        switch (profileSnapshot.child("UserType").getValue().toString()) {
            case "Student": userProfile = new StudentProfile(userName, password, name, birthday, enrolledCourses, grades);
                break;
            default: // profile type not found
                break;
        }

        // Add this RealDatabase to a StudentDatabase proxy and return it
        UserDatabase result = new StudentDatabase(this);
        return result;
    }

    @Override
    public Profile getUserProfile() {
        updateUserProfile();
        return userProfile;
    }

    @Override
    public ArrayList<CourseInterface> getScheduledCourses() {
        ArrayList<CourseInterface> result = new ArrayList<>();

        for (DataSnapshot courseID : snapshot.child("Schedule").getChildren()) {
            // get Scheduled course info
            int id, room;
            String prof, deptCode, desc;
            ArrayList<Date> startTimes, endTimes;
            ArrayList<Integer> preReqs;

            id = Integer.parseInt(courseID.getKey().toString());
            room = Integer.parseInt(courseID.child("Room").getValue().toString());
            prof = courseID.child("Prof").getValue().toString();
            deptCode = snapshot.child("Course").child(courseID.child("CourseNum").getValue().toString()).child("DeptCode").getValue().toString();
            desc = snapshot.child("Course").child(courseID.child("CourseNum").getValue().toString()).child("Description").getValue().toString();

            startTimes = new ArrayList<>();
            for (DataSnapshot startTime : courseID.child("StartTimes").getChildren()) {
                try {
                    startTimes.add(dateFormatter.parse(startTime.getValue().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            endTimes = new ArrayList<>();
            for (DataSnapshot endTime : courseID.child("EndTimes").getChildren()) {
                try {
                    endTimes.add(dateFormatter.parse(endTime.getValue().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            preReqs = new ArrayList<>();
            for (DataSnapshot prereq: snapshot.child("Course").child(courseID.child("CourseNum").getValue().toString()).child("Prerequsites").getChildren()) {
                preReqs.add(Integer.parseInt(prereq.getValue().toString()));
            }

            result.add(new ScheduledCourse(id, room, prof, deptCode, desc,
                    startTimes, endTimes, preReqs));
        }

        return result;
    }

    @Override
    public void enroll(int schedID) throws InvalidParameterException {
        DatabaseReference newSchedEntry = ref.child("Profile").child(userProfile.getUserName()).child("Courses").child("SchedEntries").push();
        newSchedEntry.setValue(schedID);

        String key = newSchedEntry.getKey();

        DatabaseReference newMarkEntry = ref.child("Profile").child(userProfile.getUserName()).child("Courses").child("Mark").child(key);
        newMarkEntry.setValue("-1");

        ref.push();
        updateUserProfile();
    }

    @Override
    public void removeCourse(int schedID) throws InvalidParameterException {
        for (DataSnapshot course : snapshot.child("Profile").child(userProfile.getUserName()).child("Courses").child("SchedEntries").getChildren()) {
            ref.child("Profile").child(userProfile.getUserName()).child("Courses").child("SchedEntries").child(course.getKey()).removeValue();
            ref.child("Profile").child(userProfile.getUserName()).child("Courses").child("Mark").child(course.getKey()).removeValue();

            ref.push();
            updateUserProfile();
        }
    }

    /**
     * Updates the userProfile with new information on the remote database.
     */
    private void updateUserProfile() {
        DataSnapshot profileSnapshot = snapshot.child("Profile").child(userProfile.getUserName());

        ArrayList<CourseInterface> enrolledCourses = new ArrayList<>();

        // key = courseID, value = grade
        HashMap<Integer, Integer> grades = new HashMap<>();

        for (DataSnapshot courseID: profileSnapshot.child("Courses").child("SchedEntries").getChildren()) {
            // get Scheduled course info
            int id, room;
            String prof, deptCode, desc;
            ArrayList<Date> startTimes, endTimes;
            ArrayList<Integer> preReqs;

            DataSnapshot scheduleSnapshot = snapshot.child("Schedule").child(courseID.getValue().toString());
            id = Integer.parseInt(scheduleSnapshot.getKey().toString());
            room = Integer.parseInt(scheduleSnapshot.child("Room").getValue().toString());
            prof = scheduleSnapshot.child("Prof").getValue().toString();
            deptCode = snapshot.child("Course").child(scheduleSnapshot.child("CourseNum").getValue().toString()).child("DeptCode").getValue().toString();
            desc = snapshot.child("Course").child(scheduleSnapshot.child("CourseNum").getValue().toString()).child("Description").getValue().toString();

            startTimes = new ArrayList<>();
            for (DataSnapshot startTime : courseID.child("StartTimes").getChildren()) {
                try {
                    startTimes.add(dateFormatter.parse(startTime.getValue().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            endTimes = new ArrayList<>();
            for (DataSnapshot endTime : courseID.child("EndTimes").getChildren()) {
                try {
                    endTimes.add(dateFormatter.parse(endTime.getValue().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            preReqs = new ArrayList<>();
            for (DataSnapshot prereq: snapshot.child("Course").child(scheduleSnapshot.child("CourseNum").getValue().toString()).child("Prerequsites").getChildren()) {
                preReqs.add(Integer.parseInt(prereq.getValue().toString()));
            }

            enrolledCourses.add(new ScheduledCourse(id, room, prof, deptCode, desc,
                    startTimes, endTimes, preReqs));

            grades.put(Integer.parseInt(courseID.getValue().toString()), Integer.parseInt(profileSnapshot.child("Courses").child("Mark").child(courseID.getKey().toString()).getValue().toString()));
        }

        // Create the profile
        switch (profileSnapshot.child("UserType").getValue().toString()) {
            case "Student": userProfile = new StudentProfile(userProfile.getUserName(), userProfile.getPassword(), userProfile.getName(), userProfile.getBirthday(), enrolledCourses, grades);
                break;
            default: // profile type not found
                break;
        }
    }
}
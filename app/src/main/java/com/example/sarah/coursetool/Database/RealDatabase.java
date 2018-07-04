package com.example.sarah.coursetool.Database;

import android.app.Application;

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
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Class that implements all database methods. The methods will be called through proxy classes
 */
public class RealDatabase extends Application implements LoginDatebaseInterface, UserDatabase, InstitutionDatabaseInterface {
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private DataSnapshot snapshot;
    private String user;
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public RealDatabase() {

    }

    @Override
    public UserDatabase getProfileDatabase(String userName, String password) throws InvalidParameterException {
        Profile loginUser = snapshot.child("Profiles").child(userName).getValue(StudentProfile.class);

        if(loginUser == null){
            throw new InvalidParameterException("Invalid username");
        } else if(!password.equals(loginUser.getPassword())){
            throw new InvalidParameterException("Invalid password");
        }

        user = loginUser.getUserName();
        return new StudentDatabase(this);
    }

    @Override
    public void addProfile(String userName, String password, String name, Date birthday) {
        ArrayList<CourseInterface> enrolledCourses = new ArrayList<>();
        HashMap<Integer, Integer> grades = new HashMap<>();
        Profile newProfile = new StudentProfile(userName, password, name, birthday, enrolledCourses, grades);
        ref.child("Profiles").child(userName).setValue(newProfile);
    }

    @Override
    public Profile getUserProfile() {
        return snapshot.child("Profiles").child(user).getValue(StudentProfile.class);
    }

    @Override
    public List<CourseInterface> getScheduledCourses() {
        return getUserProfile().getEnrolledCourses();
    }

    @Override
    public void enroll(int schedID) throws InvalidParameterException {

    }

    @Override
    public void removeCourse(int schedID) throws InvalidParameterException {

    }

    /**
     * Updates the userProfile with new information on the remote database.
     */
    private void updateUserProfile() {

    }

    @Override
    public void createCourse(String courseName, int capacity, String professor, String departmentCode,
                             String description, String prerequisites, String daysOfWeek, String startTimeStr,
                             String endTimeStr, String startDateStr, String endDateStr) {
        ArrayList<Date> startTimes = new ArrayList<>();
        ArrayList<Date> endTimes = new ArrayList<>();
        ArrayList<Integer> preReqs = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA);
        Date startDate = dateFormat.parse(startDateStr, new ParsePosition(0));
        Date endDate = dateFormat.parse(endDateStr, new ParsePosition(0));

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.CANADA);
        Date startTime = timeFormat.parse(startTimeStr, new ParsePosition(0));
        Date endTime = timeFormat.parse(endTimeStr, new ParsePosition(0));

        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(startDate);
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(endDate);

        while(calendarStart.before(calendarEnd)) {
            if ((daysOfWeek.toUpperCase().contains("M") && calendarStart.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) ||
                    (daysOfWeek.toUpperCase().contains("T") && calendarStart.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) ||
                    (daysOfWeek.toUpperCase().contains("W") && calendarStart.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) ||
                    (daysOfWeek.toUpperCase().contains("R") && calendarStart.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) ||
                    (daysOfWeek.toUpperCase().contains("F") && calendarStart.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY))
            {
                Calendar thisDate = calendarStart;

                // add class start time
                thisDate.getTime().setHours(startTime.getHours());
                thisDate.getTime().setMinutes(startTime.getMinutes());
                startTimes.add(thisDate.getTime());

                // add class end time
                thisDate.getTime().setHours(endTime.getHours());
                thisDate.getTime().setMinutes(endTime.getMinutes());
                endTimes.add(thisDate.getTime());
            }

            calendarStart.add(Calendar.DATE, 1);
        }

        // todo parse prerequisites

        for(String curr : prerequisites.split(" ")){
            preReqs.add(Integer.parseInt(curr));
        }

        String courseKey = ref.child("Courses").push().getKey();
        ScheduledCourse newCourse = new ScheduledCourse(courseKey, capacity, professor, departmentCode,
                description, startTimes, endTimes, preReqs);
    }

    /**
     * Initializes the RealDatabase and sets it to update when remote data changes
     */
    public void initDatabase() {
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                snapshot = dataSnapshot;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // failed to read new data
            }
        });
    }

}
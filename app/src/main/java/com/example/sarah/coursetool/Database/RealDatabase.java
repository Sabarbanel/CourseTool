package com.example.sarah.coursetool.Database;

import android.app.Application;

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
import java.util.Locale;
import java.util.concurrent.TimeoutException;

/**
 * Class that implements all database methods. The methods will be called through proxy classes
 */
public class RealDatabase extends Application implements LoginDatabaseInterface, UserDatabase, InstitutionDatabaseInterface {
    private static RealDatabase singleton;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private DataSnapshot snapshot;
    private String user;
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CANADA);
    private final static int timout = 5000;


    /**
     * Default constructor
     *
     * @author jdeman
     * @author nattwood
     * @date 7/10/2018
     */
    public RealDatabase() {}

    /**
     * returns realDatabase singleton
     *
     * @return RealDatabase
     * @author jdeman
     * @author nattwood
     * @date 7/10/2018
     */
    public static RealDatabase getDatabase() {
        if (singleton == null) {
            singleton = new RealDatabase();
            singleton.initDatabase();
            return singleton;
        }

        return singleton;
    }

    @Override
    public UserDatabase getProfileDatabase(String userName, String password) throws InvalidParameterException, TimeoutException {

        Profile loginUser;
        long timeoutStart = new Date().getTime();
        while(true) {
            try {
                loginUser = snapshot.child("Profiles").child(userName).getValue(StudentProfile.class);
                break;
            } catch (NullPointerException e) {
                if (new Date().getTime() - timeoutStart > timout)
                    throw new TimeoutException("Could not find profile.");
            }
        }

        if(loginUser == null){
            throw new InvalidParameterException("Invalid username");
        } else if(!password.equals(loginUser.getPassword())){
            throw new InvalidParameterException("Invalid password");
        }

        user = loginUser.getUserName();
        return new StudentDatabase(this);
    }

    @Override
    public void addProfile(final StudentProfile newProfile) {
        final String newUsername = newProfile.getUserName();
        // check if username has already been used. If not, create the new profile
        ref.child("Profiles").child(newUsername).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() == null){
                    ref.child("Profiles").child(newUsername).setValue(newProfile);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public Profile getUserProfile() throws TimeoutException {
        long timeoutStart = new Date().getTime();
        while(true) {
            try {
                return snapshot.child("Profiles").child(user).getValue(StudentProfile.class);
            } catch (NullPointerException e) {
                if (new Date().getTime() - timeoutStart > timout)
                    throw new TimeoutException("Could not find profile.");
            }
        }
    }

    @Override
    public HashMap<String, ScheduledCourse> getScheduledCourses() throws TimeoutException {
        HashMap<String, ScheduledCourse> courses = new HashMap();

        Iterable<DataSnapshot> coursesChild;
        long timeoutStart = new Date().getTime();
        while(true) {
            try {
                coursesChild = snapshot.child("Courses").getChildren();
                break;
            } catch (NullPointerException e) {
                if (new Date().getTime() - timeoutStart > timout)
                    throw new TimeoutException("Could not find courses.");
            }
        }

        for (DataSnapshot child : coursesChild) {
            ScheduledCourse pulledCourse = child.getValue(ScheduledCourse.class);

            courses.put(child.getKey(), pulledCourse);
        }

        return courses;
    }

    @Override
    public void enroll(String key) throws InvalidParameterException, TimeoutException {
        Profile profile = getUserProfile();

        ScheduledCourse course = getScheduledCourses().get(key);

        profile.getEnrolledCourses().put(key, course);

        ref.child("Profiles").child(profile.getUserName()).setValue(profile);
    }

    @Override
    public void unenrollFromCourse(String key) throws InvalidParameterException, TimeoutException {
        Profile profile = getUserProfile();

        ScheduledCourse course = getScheduledCourses().get(key);

        profile.getEnrolledCourses().remove(key);

        ref.child("Profiles").child(profile.getUserName()).setValue(profile);
    }

    @Override
    public String createCourse(String courseName, int capacity, String professor, String departmentCode,
                             String description, String prerequisites, String daysOfWeek, String startTimeStr,
                             String endTimeStr, String startDateStr, String endDateStr) {
        ArrayList<Date> startTimes = new ArrayList<>();
        ArrayList<Date> endTimes = new ArrayList<>();
        ArrayList<String> preReqs = new ArrayList<>();

        // parse the start date and end date
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

        Calendar calendarStartTime = Calendar.getInstance();
        calendarStartTime.setTime(startTime);
        Calendar calendarEndTime = Calendar.getInstance();
        calendarEndTime.setTime(endTime);

        while(calendarStart.before(calendarEnd) || calendarStart.get(Calendar.DATE) == calendarEnd.get(Calendar.DATE)) {
            if ((daysOfWeek.toUpperCase().contains("M") && calendarStart.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) ||
                    (daysOfWeek.toUpperCase().contains("T") && calendarStart.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) ||
                    (daysOfWeek.toUpperCase().contains("W") && calendarStart.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) ||
                    (daysOfWeek.toUpperCase().contains("R") && calendarStart.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) ||
                    (daysOfWeek.toUpperCase().contains("F") && calendarStart.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY))
            {
                // add class start time
                Calendar thisDate = (Calendar) calendarStart.clone();
                thisDate.set(Calendar.HOUR_OF_DAY, calendarStartTime.get(Calendar.HOUR_OF_DAY));
                thisDate.set(Calendar.MINUTE, calendarStartTime.get(Calendar.MINUTE));
                startTimes.add(thisDate.getTime());

                // add class end time
                thisDate.set(Calendar.HOUR_OF_DAY, calendarEndTime.get(Calendar.HOUR_OF_DAY));
                thisDate.set(Calendar.MINUTE, calendarEndTime.get(Calendar.MINUTE));
                endTimes.add(thisDate.getTime());
            }

            calendarStart.add(Calendar.DATE, 1);
        }

        for(String curr : prerequisites.split(",")){
            preReqs.add(curr.trim().toUpperCase());
        }

        String courseKey = ref.child("Courses").push().getKey();
        ScheduledCourse newCourse = new ScheduledCourse(courseKey, capacity, professor, departmentCode,
                description, startTimes, endTimes, preReqs);

        ref.child("Courses").child(courseKey).setValue(newCourse);

        return courseKey;
    }

    @Override
    public void removeCourse(String key) {
        ref.child("Courses").child(key).removeValue();
    }

    /**
     * Initializes the RealDatabase and sets it to update when remote data changes
     *
     * @author jdeman
     * @author nattwood
     * @date 7/10/2018
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
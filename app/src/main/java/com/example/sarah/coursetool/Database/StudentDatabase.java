package com.example.sarah.coursetool.Database;

import com.example.sarah.coursetool.Course.ScheduledCourse;
import com.example.sarah.coursetool.UserProfile.Profile;

import java.security.InvalidParameterException;
import java.util.HashMap;

/**
 * A proxy class for performing database operations related to student functionality.
 */
public class StudentDatabase implements UserDatabase {
    RealDatabase database;

    /**
     * Constructs new StudentDatabase. Normal used by a loginDatabase when correct credentials are supplied.
     * @param sourceDatabase
     */
    protected StudentDatabase(RealDatabase sourceDatabase) {
        database = sourceDatabase;
    }

    public static StudentDatabase StudentDatabase() {
        return new StudentDatabase(RealDatabase.getDatabase());
    }

    @Override
    public Profile getUserProfile() {
        return database.getUserProfile();
    }

    @Override
    public HashMap<String, ScheduledCourse> getScheduledCourses() {
        return database.getScheduledCourses();
    }

    @Override
    public void enroll(String key) throws InvalidParameterException {
        database.enroll(key);
    }

    @Override
    public void removeCourse(String key) throws InvalidParameterException {
        database.removeCourse(key);
    }
}

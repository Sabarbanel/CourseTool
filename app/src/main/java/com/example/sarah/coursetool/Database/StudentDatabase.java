package com.example.sarah.coursetool.Database;

import com.example.sarah.coursetool.Course.CourseInterface;
import com.example.sarah.coursetool.Course.ScheduledCourse;
import com.example.sarah.coursetool.UserProfile.Profile;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDatabase implements UserDatabase {
    RealDatabase database;

    /**
     * Constructs new StudentDatabase. Normal used by a loginDatabase when correct credentials are supplied.
     * @param sourceDatabase
     */
    protected StudentDatabase(RealDatabase sourceDatabase) {
        database = sourceDatabase;
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

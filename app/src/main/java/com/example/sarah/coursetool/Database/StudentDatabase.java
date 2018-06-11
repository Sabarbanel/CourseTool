package com.example.sarah.coursetool.Database;

import com.example.sarah.coursetool.Course.CourseInterface;
import com.example.sarah.coursetool.UserProfile.Profile;

import java.security.InvalidParameterException;
import java.util.ArrayList;

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
    public ArrayList<CourseInterface> getScheduledCourses() {
        return database.getScheduledCourses();
    }

    @Override
    public void enroll(int schedID) throws InvalidParameterException {
        database.enroll(schedID);
    }

    @Override
    public void removeCourse(int schedID) throws InvalidParameterException {
        database.removeCourse(schedID);
    }
}

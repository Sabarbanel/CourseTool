package com.example.sarah.coursetool.Database;

import com.example.sarah.coursetool.Course.CourseInterface;
import com.example.sarah.coursetool.Course.ScheduledCourse;
import com.example.sarah.coursetool.UserProfile.Profile;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Interface for a Database that has access to a users information and course schedule
 */
public interface UserDatabase {

    /**
     * Gets the user's profile
     * @return userProfile
     */
    Profile getUserProfile();

    /**
     * Gets all available courses
     * @return scheduleCourses
     */
    HashMap<String, ScheduledCourse> getScheduledCourses();


    /**
     * Enrolls the user in a course
     * @param key
     * @throws InvalidParameterException
     */
    void enroll(String key) throws InvalidParameterException;

    /**
     * Removes the user from a course
     * @param key
     * @throws InvalidParameterException
     */
    void removeCourse (String key) throws InvalidParameterException;

}

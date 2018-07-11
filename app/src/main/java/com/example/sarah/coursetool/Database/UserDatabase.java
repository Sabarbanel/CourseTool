package com.example.sarah.coursetool.Database;

import com.example.sarah.coursetool.Course.ScheduledCourse;
import com.example.sarah.coursetool.UserProfile.Profile;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * Interface for a Database that has access to a users information and course schedule
 */
public interface UserDatabase {

    /**
     * Gets the user's profile
     * @return userProfile
     */
    Profile getUserProfile() throws TimeoutException;

    /**
     * Gets all available courses
     * @return scheduleCourses
     */
    HashMap<String, ScheduledCourse> getScheduledCourses() throws TimeoutException;


    /**
     * Enrolls the user in a course
     * @param key
     * @throws InvalidParameterException
     *
     * @author jdeman
     * @author nattwood
     * @date 7/10/2018
     */
    void enroll(String key) throws InvalidParameterException, TimeoutException;

    /**
     * Removes the user from a course
     * @param key
     * @throws InvalidParameterException
     *
     * @author jdeman
     * @author nattwood
     * @date 7/10/2018
     */
    void unenrollFromCourse (String key) throws InvalidParameterException, TimeoutException;

}

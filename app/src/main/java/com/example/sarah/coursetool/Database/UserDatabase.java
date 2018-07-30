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
     * Gets the courses that the user is enrolled in
     * @return enrolled classes, with the key as the course's key in the DB
     * @throws TimeoutException
     * @date 7/16/2018
     */
    HashMap<String, ScheduledCourse> getEnrolledCourses() throws TimeoutException;

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

    /**
     * Changes the users password
     * @param newPassword
     *
     * @Date 23/07/2018
     * @Author jdeman
     * @Author rayub
     */
    void changePassword (String newPassword) throws TimeoutException;
}

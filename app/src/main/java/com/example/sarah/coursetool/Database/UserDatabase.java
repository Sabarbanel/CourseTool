package com.example.sarah.coursetool.Database;

import com.example.sarah.coursetool.Course.CourseInterface;
import com.example.sarah.coursetool.UserProfile.Profile;

import java.security.InvalidParameterException;
import java.util.List;

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
    List<CourseInterface> getScheduledCourses();


    /**
     * Enrolls the user in a course
     * @param schedID
     * @throws InvalidParameterException
     */
    void enroll(int schedID) throws InvalidParameterException;

    /**
     * Removes the user from a course
     * @param schedID
     * @throws InvalidParameterException
     */
    void removeCourse (int schedID) throws InvalidParameterException;

}

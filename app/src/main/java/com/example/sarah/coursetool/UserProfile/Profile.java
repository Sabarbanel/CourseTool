package com.example.sarah.coursetool.UserProfile;

import com.example.sarah.coursetool.Course.ScheduledCourse;

import java.util.Date;
import java.util.HashMap;

/**
 * Interface for profiles
 */
public interface Profile {

    /**
     * Gets the profile's username
     *
     * @return username
     */
    String getUserName();

    /**
     * Gets the profile's username
     *
     * @return password
     */
    String getPassword();

    /**
     * Gets the profiles first and last name
     *
     * @return name
     */
    String getName();

    /**
     * Gets this profiles birthday date
     *
     * @return birthday
     */
    Date getBirthday();

    /**
     * Gets this profiles grade in a course
     *
     * @param CourseID
     * @return grade
     */
    int getCourseGrade(int CourseID);

    /**
     * Gets courses associated with this profile (courses completed, in progress, and signed up for)
     * @return
     */
    HashMap<String, ScheduledCourse> getEnrolledCourses();
}

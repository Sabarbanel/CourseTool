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
     * Gets this user's grade in a specific course
     *
     * @param courseKey - the unique key for the course from the DB
     * @return grade - the grade achieved in the course
     */
    int getCourseGrade(String courseKey);

    /**
     * Gets all the course grades for this user
     *
     * @date 7/18/2018
     * @author nattwood
     * @author lToal
     * @return
     */
    HashMap<String, Integer> getGrades();

    /**
     * Gets courses associated with this profile (courses completed, in progress, and signed up for)
     * @return
     */
    HashMap<String, ScheduledCourse> getEnrolledCourses();
}

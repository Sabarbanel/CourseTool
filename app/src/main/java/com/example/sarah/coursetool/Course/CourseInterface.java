package com.example.sarah.coursetool.Course;

import java.util.ArrayList;
import java.util.Date;

/**
 * Interface for an instance of a scheduled course
 */
public interface CourseInterface {

    /**
     * Gets the name for the scheduled course
     * @return name
     */
    String getName();

    /**
     * Gets the unique ID for the scheduled course
     * @return ID
     */
    String getID();

    /**
     * Gets the profs name
     * @return profName
     */
    String getProf();

    /**
     * Gets the course room number
     * @return roomNum
     */
    int getCapacity();

    /**
     * Gets an Arraylist of start times for a course
     * @return startTimes
     */
    ArrayList<Date> getStartTimes();

    /**
     * Gets an Arraylist of end times for a course
     * @return endTimes
     */
    ArrayList<Date> getEndTimes();

    /**
     * Gets the dept code
     * @return deptCode
     */
    String getDeptCode();

    /**
     * Gets the course desc
     * @return courseDesc
     */
    String getDesc();

    /**
     * Gets an ArrayList of course IDs they require to take this course
     * @return prereqs
     */
    ArrayList<String> getPrereqs();

    /**
     * Returns the number of students enrolled in the course
     * @return enrolled
     */
    int getEnrolled();

    /**
     * Increments the enrollment count
     */
    void incrementEnrolled();

    /**
     * Decrements the enrollment count
     */
    void decrementEnrolled();
}

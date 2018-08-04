/**
 * @author: Jerrett and Sarah
 * An implementation of the CourseInterface interface
 * Provides an object for representing a course.
 */
package com.example.sarah.coursetool.Course;

import java.util.ArrayList;
import java.util.Date;

/**
 * A class for storing details about courses offered by the institution
 */
public class ScheduledCourse implements CourseInterface {
    public String name;
    public String id;
    public int capacity;
    public int enrolled;
    public String prof, deptCode, desc;
    public ArrayList<Date> startTimes, endTimes;
    public ArrayList<String> preReqs = new ArrayList<String>();

    public ScheduledCourse(){
        // empty constructor for Firebase
    }

    public ScheduledCourse(String courseName, String id, int capacity, String prof, String deptCode, String desc,
                           ArrayList<Date> startTimes, ArrayList<Date> endTimes, ArrayList<String> preReqs, int enrolled) {
        this.name = courseName;
        this.id = id;
        this.capacity = capacity;
        this.prof = prof;
        this.deptCode = deptCode;
        this.desc = desc;
        this.startTimes = startTimes;
        this.endTimes = endTimes;
        this.preReqs = preReqs;
        this.enrolled = enrolled;
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public String getProf() {
        return prof;
    }

    @Override
    public String getDeptCode() {
        return deptCode;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public ArrayList<Date> getStartTimes() {
        return startTimes;
    }

    @Override
    public ArrayList<Date> getEndTimes() {
        return endTimes;
    }

    @Override
    public ArrayList<String> getPreReqs() {
        return preReqs;
    }

    @Override
    public int getEnrolled() { return enrolled; }

    @Override
    public void incrementEnrolled() { enrolled++; }

    @Override
    public void decrementEnrolled() { enrolled--; }
}

package com.example.sarah.coursetool.Course;

import java.util.ArrayList;
import java.util.Date;

public class ScheduledCourse implements CourseInterface {
    private String id;
    private int capacity;
    private String prof, deptCode, desc;
    private ArrayList<Date> startTimes, endTimes;
    private ArrayList<String> preReqs;

    public ScheduledCourse(){
        // empty constructor for Firebase
    }

    public ScheduledCourse(String id, int capacity, String prof, String deptCode, String desc,
                           ArrayList<Date> startTimes, ArrayList<Date> endTimes, ArrayList<String> preReqs) {
        this.id = id;
        this.capacity = capacity;
        this.prof = prof;
        this.deptCode = deptCode;
        this.desc = desc;
        this.startTimes = startTimes;
        this.endTimes = endTimes;
        this.preReqs = preReqs;
    }

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
    public ArrayList<String> getPrereqs() {
        return preReqs;
    }
}

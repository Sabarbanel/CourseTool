package com.example.sarah.coursetool.Course;

import java.util.ArrayList;
import java.util.Date;

public class ScheduledCourse implements CourseInterface {
    private int id, room;
    private String prof, deptCode, desc;
    private ArrayList<Date> startTimes, endTimes;
    private ArrayList<Integer> preReqs;

    public ScheduledCourse(int id, int room, String prof, String deptCode, String desc,
                           ArrayList<Date> startTimes, ArrayList<Date> endTimes, ArrayList<Integer> preReqs) {
        this.id = id;
        this.room = room;
        this.prof = prof;
        this.deptCode = deptCode;
        this.desc = desc;
        this.startTimes = startTimes;
        this.endTimes = endTimes;
        this.preReqs = preReqs;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public int getRoom() {
        return room;
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
    public ArrayList<Integer> getPrereqs() {
        return preReqs;
    }
}

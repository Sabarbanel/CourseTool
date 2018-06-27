package com.example.sarah.coursetool.CourseListing;

import java.util.ArrayList;
import java.util.Date;

public class CourseListing {
    //the data points of a course from the actual database, to be used as a reference for the data this object needs
    /*int id, room;
    String prof, deptCode, desc;
    ArrayList<Date> startTimes, endTimes;
    ArrayList<Integer> preReqs;*/
//push test
    public String courseTitle;
    public String courseProf;
    public String courseDepartment;
    public String courseDescription;

    public Date courseStartTime; //this may be the wrong variable type, to be potentially changed later
    public Date courseEndTime; //this may be the wrong variable type, to be potentially changed later

    public int courseID;
    public int courseRoom;
    ArrayList<Integer> coursePreqs;

    public CourseListing (String title, String prof, String dept, String desc, Date start, Date end, int id, int room) {
        this.courseTitle = title;
        this.courseProf = prof;
        this.courseDepartment = dept;
        this.courseDescription = desc;
        this.courseStartTime = start;
        this.courseEndTime = end;
        this.courseID = id;
        this.courseRoom = room;
        coursePreqs = new ArrayList<>();
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseProf(String courseProf) {
        this.courseProf = courseProf;
    }

    public String getCourseProf() {
        return courseProf;
    }

    public void setCourseDepartment(String courseDepartment) {
        this.courseDepartment = courseDepartment;
    }

    public String getCourseDepartment() {
        return courseDepartment;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseStartTime(Date courseStartTime) {
        this.courseStartTime = courseStartTime;
    }

    public Date getCourseStartTime() {
        return courseStartTime;
    }

    public void setCourseEndTime(Date courseEndTime) {
        this.courseEndTime = courseEndTime;
    }

    public Date getCourseEndTime() {
        return courseEndTime;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseRoom(int courseRoom) {
        this.courseRoom = courseRoom;
    }

    public int getCourseRoom() {
        return courseRoom;
    }

    public void loadPreReq (int c) {
        if (coursePreqs.contains(c)) {
            return;
        } else {
            coursePreqs.add(c);
        }
    }

    public void removePreReq (int c) {
        if (coursePreqs.contains(c)) {
            coursePreqs.remove(c);
        } else {
            return;
        }
    }

    public String getPreReqs () {
        String s = "";
        for (int i = 0; i < coursePreqs.size(); i++) {
            s += coursePreqs.get(i) + ", ";
        }
        return s;
    }

    @Override
    public String toString() {
        String s;
        s = this.getCourseTitle() + " , " + this.getCourseID() + " , " + this.getCourseProf() ;
        return s;
    }
}

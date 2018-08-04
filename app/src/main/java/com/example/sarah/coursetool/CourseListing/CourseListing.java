/**
 * @author: Lauchlan and Noah S
 * CourseListing
 * Provides an object for representing a course in the courses display.
 */
package com.example.sarah.coursetool.CourseListing;

import com.example.sarah.coursetool.Course.ScheduledCourse;

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
    public String courseUniqueID;

    public Date courseStartTime; //this may be the wrong variable type, to be potentially changed later
    public Date courseEndTime; //this may be the wrong variable type, to be potentially changed later

    public int courseID;
    public int courseRoom;
    public int capacity = 0;
    public int enrolled = 0;
    public int courseGrade = -1;
    ArrayList<String> coursePreqs = new ArrayList<String>();
    int[] courseDays;

    public CourseListing (String title, String prof, String dept, String desc, Date start, Date end, int id, int room, ArrayList<String> prereq) {
        this.courseTitle = title;
        this.courseProf = prof;
        this.courseDepartment = dept;
        this.courseDescription = desc;
        this.courseStartTime = start;
        this.courseEndTime = end;
        this.courseID = id;
        this.courseRoom = room;
        //coursePreqs = new ArrayList<String>(prereq);
        coursePreqs.clear();
        for (int i = 0; i < prereq.size(); i++) {
            if (!(prereq.get(i).equals("")) || !(prereq.get(i).equals(" "))) {
                coursePreqs.add(prereq.get(i));
            }
        }
        this.courseDays = new int[7];
        this.courseUniqueID = "Hello";
    }

    public CourseListing (ScheduledCourse course) {
        this.courseTitle = course.getName();
        this.courseProf = course.getProf();
        this.courseDepartment = course.getDeptCode();
        this.courseDescription = course.getDesc();
        this.courseStartTime = course.getStartTimes().get(0);
        this.courseEndTime = course.getEndTimes().get(course.getEndTimes().size()-1);
        this.courseUniqueID = course.getID();
        this.courseID = 1020;
        this.courseRoom = 0;
        this.courseDays = new int[7];
        for(int i = 0; i < course.getStartTimes().size(); i++) {
            courseDays[course.getStartTimes().get(i).getDay()] |= 1;
        }

        if (!coursePreqs.isEmpty()) {
            for (int i = 0; i < course.getPreReqs().size(); i++) {
                this.coursePreqs.add(course.getPreReqs().get(i));
            }
        } else {
            this.coursePreqs = new ArrayList<String>();
        }
        /*if(coursePreqs == null) {
            this.coursePreqs = new ArrayList<String>();
        }*/
        this.capacity = course.getCapacity();
        this.enrolled = course.getEnrolled();
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

    public void loadPreReq (String c) {
        if (coursePreqs.contains(c)) {
            return;
        } else {
            coursePreqs.add(c);
        }
    }

    public void removePreReq (String c) {
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

    public ArrayList<String> getCoursePreqs(){
        return coursePreqs;
    }

    public void addPrereq(String listing){
        coursePreqs.add(listing);
        //add a fail-safe to make sure same course isnt added as prereq again
    }

    public String getPrereq(int i){
        if (i > coursePreqs.size()) {
            return null;
        }
        return coursePreqs.get(i);
    }

    public int getPreqSize () {
        return coursePreqs.size();
    }

    @Override
    public String toString() {
        String s;
        s = this.getCourseTitle() + " , " + this.getCourseID() + " , " + this.getCourseProf() ;
        return s;
    }
}

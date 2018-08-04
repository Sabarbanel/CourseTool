/**
 * @author: Lauchlan and Noah S
 * DataGenerator
 * Provides a singleton to act as an access point to the database.
 */

package com.example.sarah.coursetool.CourseListing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.example.sarah.coursetool.Course.ScheduledCourse;
import com.example.sarah.coursetool.Database.RealDatabase;
import com.example.sarah.coursetool.UserProfile.StudentProfile;

public class DataGenerator {

    private static DataGenerator dataGenerator = null;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private DataGenerator() {

    }

    public static DataGenerator getGenerator() {
        if(dataGenerator == null) {
            dataGenerator = new DataGenerator();
        }
        return dataGenerator;
    }

    public void getAllCourses(ArrayList<CourseListing> inputData) {

        inputData.clear();
        RealDatabase conn = RealDatabase.getDatabase();
        while(conn.snapshotIsNull()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        HashMap<String, ScheduledCourse> courses;
        try {
            courses = conn.getScheduledCourses();
        } catch (TimeoutException e) {
            return;
        }
        for(Map.Entry<String, ScheduledCourse> course:courses.entrySet()) {
            CourseListing inputCourse = new CourseListing(course.getValue());
            inputData.add(inputCourse);
        }
    }

    public void getFallCourses(ArrayList<CourseListing> inputData) {
        inputData.clear();
        RealDatabase conn = RealDatabase.getDatabase();
        while(conn.snapshotIsNull()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        HashMap<String, ScheduledCourse> courses;
        try {
            courses = conn.getScheduledCourses();
        } catch (TimeoutException e) {
            return;
        }
        for(Map.Entry<String, ScheduledCourse> course:courses.entrySet()) {
            CourseListing inputCourse = new CourseListing(course.getValue());
            Date fallStart, fallEnd;
            try {
                fallStart = sdf.parse("30/8/2018");
                fallEnd = sdf.parse("1/1/2019");
            } catch (ParseException e) {
                return;
            }
            if(inputCourse.courseStartTime.after(fallStart) && inputCourse.courseEndTime.before(fallEnd)) {
                inputData.add(inputCourse);
            }
        }
    }

    public void getWinterCourses(ArrayList<CourseListing> inputData) {
        inputData.clear();
        RealDatabase conn = RealDatabase.getDatabase();
        while(conn.snapshotIsNull()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        HashMap<String, ScheduledCourse> courses;
        try {
            courses = conn.getScheduledCourses();
        } catch (TimeoutException e) {
            return;
        }
        for(Map.Entry<String, ScheduledCourse> course:courses.entrySet()) {
            CourseListing inputCourse = new CourseListing(course.getValue());
            Date winterStart, winterEnd;
            try {
                winterStart = sdf.parse("30/12/2018");
                winterEnd = sdf.parse("1/5/2019");
            } catch (ParseException e) {
                return;
            }
            if(inputCourse.courseStartTime.after(winterStart) && inputCourse.courseEndTime.before(winterEnd)) {
                inputData.add(inputCourse);
            }
        }
    }

    public void getSummerCourses(ArrayList<CourseListing> inputData) {
        inputData.clear();
        RealDatabase conn = RealDatabase.getDatabase();
        while(conn.snapshotIsNull()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        HashMap<String, ScheduledCourse> courses;
        try {
            courses = conn.getScheduledCourses();
        } catch (TimeoutException e) {
            return;
        }
        for(Map.Entry<String, ScheduledCourse> course:courses.entrySet()) {
            CourseListing inputCourse = new CourseListing(course.getValue());
            Date summerStart, summerEnd;
            try {
                summerStart = sdf.parse("30/4/2018");
                summerEnd = sdf.parse("1/9/2018");
            } catch (ParseException e) {
                return;
            }
            if(inputCourse.courseStartTime.after(summerStart) && inputCourse.courseEndTime.before(summerEnd)) {
                inputData.add(inputCourse);
            }
        }
    }

    public void getFacultySpecificCourses(ArrayList<CourseListing> inputData, String filter) {
        inputData.clear();
        RealDatabase conn = RealDatabase.getDatabase();
        while(conn.snapshotIsNull()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        HashMap<String, ScheduledCourse> courses;
        try {
            courses = conn.getScheduledCourses();
        } catch (TimeoutException e) {
            return;
        }
        for(Map.Entry<String, ScheduledCourse> course:courses.entrySet()) {
            CourseListing inputCourse = new CourseListing(course.getValue());

            if(inputCourse.courseDepartment.substring(0,4).toLowerCase().equals(filter.toLowerCase())) {
                inputData.add(inputCourse);
            }
        }
    }

    public void getDaySchedule(Calendar day, ArrayList<CourseListing> inputData) {
        inputData.clear();
        ArrayList<CourseListing> unsortedData = new ArrayList<CourseListing>();
        RealDatabase conn = RealDatabase.getDatabase();
        int counter = 0;
        while(conn.snapshotIsNull() && counter < 2) {
            try {
                counter++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        StudentProfile profile;
        try {
            profile = (StudentProfile) conn.getUserProfile();
        } catch (TimeoutException e) {
            return;
        }
        HashMap<String, ScheduledCourse> courses = profile.getEnrolledCourses();
        for(Map.Entry<String, ScheduledCourse> course:courses.entrySet()) {
            CourseListing inputCourse = new CourseListing(course.getValue());
            if(inputCourse.courseDays[day.get(Calendar.DAY_OF_WEEK) - 1] == 1 && inputCourse.courseStartTime.before(day.getTime()) && inputCourse.courseEndTime.after(day.getTime())) {
                unsortedData.add(inputCourse);
            }
        }
        while(unsortedData.size() > 0) {
            CourseListing minCourse = unsortedData.get(0);
            int i = 1;
            while(i < unsortedData.size()) {
                if(unsortedData.get(i).courseStartTime.before(minCourse.courseStartTime)) {
                    minCourse = unsortedData.get(i);
                }
                i++;
            }
            inputData.add(minCourse);
            unsortedData.remove(minCourse);
        }
    }

    public void getCompletedCourses(ArrayList<CourseListing> inputData) {
        inputData.clear();
        RealDatabase conn = RealDatabase.getDatabase();
        int counter = 0;
        while(conn.snapshotIsNull() && counter < 2) {
            try {
                counter++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        StudentProfile profile;
        try {
            profile = (StudentProfile) conn.getUserProfile();
        } catch (TimeoutException e) {
            return;
        }
        HashMap<String, ScheduledCourse> courses = profile.getEnrolledCourses();
        for(Map.Entry<String, ScheduledCourse> course:courses.entrySet()) {
            CourseListing inputCourse = new CourseListing(course.getValue());
            inputData.add(inputCourse);
        }
    }

    public String getGrade(String courseCode) {
        RealDatabase conn = RealDatabase.getDatabase();
        int counter = 0;
        while(conn.snapshotIsNull() && counter < 2) {
            try {
                counter++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(conn.snapshotIsNull()) {
            return "IP";
        }
        StudentProfile profile;
        try {
            profile = (StudentProfile) conn.getUserProfile();
        } catch (TimeoutException e) {
            return "IP";
        }
        int grade = profile.getCourseGrade(courseCode);
        if(grade != -1){
            return Integer.toString(grade);
        } else {
            return "IP";
        }
    }

    }



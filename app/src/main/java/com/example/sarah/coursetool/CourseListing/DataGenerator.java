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

import android.util.Log;

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

        /*Date start1 = new Date(1967, 01, 04);
        Date end1 = new Date(1970, 04, 05);
        Date start2 = new Date(2017, 01, 06);
        Date end2 = new Date(2017, 4, 07);
        Date start3 = new Date(1960, 01, 02);
        Date end3 = new Date(1970, 05, 03);
        Date start4 = new Date(1980, 01, 01);
        Date end4 = new Date(1990, 8, 01);
        Date start5 = new Date(1900, 04, 02);
        Date end5 = new Date(1997, 8, 03);

        CourseListing course1 = new CourseListing("Introduction to the Tyrannosaurus Rex",
                "Dr Fossils", "Archaeology",
                "Not for the faint of heart", start1, end1,
                275638, 4);

        CourseListing course2 = new CourseListing("All About Rocks",
                "Dr Stone", "Archaeology",
                "Learning rocks!", start2, end2,
                14506, 9);

        CourseListing course3 = new CourseListing("Advanced Animal Behaviour",
                "Dr Moose", "Psychology",
                "Cats, dogs, and more!", start3, end3,
                18013, 6);

        CourseListing course4 = new CourseListing("Introduction to Data Structures",
                "Dr Bitwise", "Computer Science",
                "course4.desc = \"Fun course!\"", start4, end4,
                34829, 7);

        CourseListing course5 = new CourseListing("How to Succeed in Business",
                "Dr Moneybags", "Business",
                "Learn how to make money from others for only $1350!", start5, end5,
                40392, 12);

        inputData.clear();
        inputData.add(course1);
        inputData.add(course2);
        inputData.add(course3);
        inputData.add(course4);
        inputData.add(course5);
        inputData.add(course1);
        inputData.add(course2);
        inputData.add(course3);
        inputData.add(course4);
        inputData.add(course5);
        inputData.add(course1);
        inputData.add(course2);
        inputData.add(course3);
        inputData.add(course4);
        inputData.add(course5);*/
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
            Log.d("Timeouterror",e.toString());
            return;
        }
        for(Map.Entry<String, ScheduledCourse> course:courses.entrySet()) {
            CourseListing inputCourse = new CourseListing(course.getValue());
            inputData.add(inputCourse);
        }
    }

    public void getFallCourses(ArrayList<CourseListing> inputData) {
        /*Date start4 = new Date(1967, 01, 04);
        Date end4 = new Date(1990, 8, 01);
        CourseListing course4 = new CourseListing("Introduction to Data Structures",
                "Dr Bitwise", "Computer Science",
                "course4.desc = \"Fun course!\"", start4, end4,
                34829, 7);
        inputData.clear();
        inputData.add(course4);
        inputData.add(course4);
        inputData.add(course4);*/
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
            Log.d("Timeouterror",e.toString());
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
        /*Date start4 = new Date(1967, 01, 04);
        Date end4 = new Date(1990, 8, 01);
        CourseListing course4 = new CourseListing("Introduction to Data Structures",
                "Dr Bitwise", "Computer Science",
                "course4.desc = \"Fun course!\"", start4, end4,
                34829, 7);
        inputData.clear();
        inputData.add(course4);
        inputData.add(course4);
        inputData.add(course4);*/
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
            Log.d("Timeouterror",e.toString());
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
        /*Date start4 = new Date(1967, 01, 04);
        Date end4 = new Date(1990, 8, 01);
        CourseListing course4 = new CourseListing("Introduction to Data Structures",
                "Dr Bitwise", "Computer Science",
                "course4.desc = \"Fun course!\"", start4, end4,
                34829, 7);
        inputData.clear();
        inputData.add(course4);
        inputData.add(course4);
        inputData.add(course4);*/
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
            Log.d("Timeouterror",e.toString());
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

    public void getCSCICourses(ArrayList<CourseListing> inputData) {
        /*Date start4 = new Date(1967, 01, 04);
        Date end4 = new Date(1990, 8, 01);
        CourseListing course4 = new CourseListing("Introduction to Data Structures",
                "Dr Bitwise", "Computer Science",
                "course4.desc = \"Fun course!\"", start4, end4,
                34829, 7);
        inputData.clear();
        inputData.add(course4);
        inputData.add(course4);
        inputData.add(course4);*/
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
            Log.d("Timeouterror",e.toString());
            return;
        }
        for(Map.Entry<String, ScheduledCourse> course:courses.entrySet()) {
            CourseListing inputCourse = new CourseListing(course.getValue());
            if(inputCourse.courseDepartment.substring(0,4).equals("CSCI")) {
                inputData.add(inputCourse);
            }
        }
    }

    public void getDaySchedule(Calendar day, ArrayList<CourseListing> inputData) {
        inputData.clear();
        /*RealDatabase conn = RealDatabase.getDatabase();
        while(conn.snapshotIsNull()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        HashMap<String, ScheduledCourse> courses = conn.getScheduledCourses();
        for(Map.Entry<String, ScheduledCourse> course:courses.entrySet()) {
            CourseListing inputCourse = new CourseListing(course.getValue());
            inputData.add(inputCourse);
        }*/
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
            getFakeCourses(inputData);
            return;
        }
        StudentProfile profile;
        try {
            profile = (StudentProfile) conn.getUserProfile();
        } catch (TimeoutException e) {
            Log.d("Timeouterror",e.toString());
            return;
        }
        HashMap<String, ScheduledCourse> courses = profile.getEnrolledCourses();
        for(Map.Entry<String, ScheduledCourse> course:courses.entrySet()) {
            CourseListing inputCourse = new CourseListing(course.getValue());
            //Log.d("monkey","day is "+day.getTime()+" course start is "+inputCourse.courseStartTime+" course end is "+inputCourse.courseEndTime);
            if(inputCourse.courseDays[day.get(Calendar.DAY_OF_WEEK) - 1] == 1 && inputCourse.courseStartTime.before(day.getTime()) && inputCourse.courseEndTime.after(day.getTime())) {
                //Log.d("monkey","start "+course.getValue().getStartTimes().get(0).getDate()+" current " + day.getTime().getDate() + " end "+course.getValue().getEndTimes().get(course.getValue().getEndTimes().size()-1).getDate());
                //Log.d("monkey","day is "+day.getTime()+" course start is "+inputCourse.courseStartTime+" course end is "+inputCourse.courseEndTime);
                inputData.add(inputCourse);
            }
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
        if(conn.snapshotIsNull()) {
            getFakeCourses(inputData);
            return;
        }
        StudentProfile profile;
        try {
            profile = (StudentProfile) conn.getUserProfile();
        } catch (TimeoutException e) {
            Log.d("Timeouterror",e.toString());
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
            Log.d("Timeouterror",e.toString());
            return "IP";
        }
        int grade = profile.getCourseGrade(courseCode);
        if(grade != -1){
            return Integer.toString(grade);
        } else {
            return "IP";
        }
    }

    public void getFakeCourses(ArrayList<CourseListing> inputData) {
        Date start1 = new Date(1967, 01, 04);
        Date end1 = new Date(1970, 04, 05);
        Date start2 = new Date(2017, 01, 06);
        Date end2 = new Date(2017, 4, 07);
        Date start3 = new Date(1960, 01, 02);
        Date end3 = new Date(1970, 05, 03);
        Date start4 = new Date(1980, 01, 01);
        Date end4 = new Date(1990, 8, 01);
        Date start5 = new Date(1900, 04, 02);
        Date end5 = new Date(1997, 8, 03);

        CourseListing course1 = new CourseListing("Introduction to the Tyrannosaurus Rex",
                "Dr Fossils", "Archaeology",
                "Not for the faint of heart", start1, end1,
                275638, 4);

        CourseListing course2 = new CourseListing("All About Rocks",
                "Dr Stone", "Archaeology",
                "Learning rocks!", start2, end2,
                14506, 9);

        CourseListing course3 = new CourseListing("Advanced Animal Behaviour",
                "Dr Moose", "Psychology",
                "Cats, dogs, and more!", start3, end3,
                18013, 6);

        CourseListing course4 = new CourseListing("Introduction to Data Structures",
                "Dr Bitwise", "Computer Science",
                "course4.desc = \"Fun course!\"", start4, end4,
                34829, 7);

        CourseListing course5 = new CourseListing("How to Succeed in Business",
                "Dr Moneybags", "Business",
                "Learn how to make money from others for only $1350!", start5, end5,
                40392, 12);

        inputData.clear();
        inputData.add(course1);
        inputData.add(course2);
        inputData.add(course3);
        inputData.add(course4);
        inputData.add(course5);
        inputData.add(course1);
        inputData.add(course2);
        inputData.add(course3);
        inputData.add(course4);
        inputData.add(course5);
        inputData.add(course1);
        inputData.add(course2);
        inputData.add(course3);
        inputData.add(course4);
        inputData.add(course5);
    }

}

package com.example.sarah.coursetool.UserProfile;

import com.example.sarah.coursetool.Course.CourseInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class StudentProfile implements Profile {
    String userName, password, name;
    Date birthday;
    ArrayList<CourseInterface> enrolledCourses;

    // key = courseID, value = grade
    HashMap<Integer, Integer> grades;

    public StudentProfile(String userName, String password, String name, Date birthday,
                          ArrayList<CourseInterface> enrolledCourses, HashMap<Integer, Integer> grades) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.enrolledCourses = enrolledCourses;
        this.grades = grades;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Date getBirthday() {
        return birthday;
    }

    public ArrayList<CourseInterface> getEnrolledCourses() {
        return enrolledCourses;
    }

    @Override
    public int getCourseGrade(int CourseID) {
        return grades.get(CourseID);
    }
}

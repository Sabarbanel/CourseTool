package com.example.sarah.coursetool.UserProfile;

import com.example.sarah.coursetool.Course.CourseInterface;

import java.util.Date;
import java.util.HashMap;

public class StudentProfile implements Profile {
    String userName, password, name;
    Date birthday;
    HashMap<String, CourseInterface> enrolledCourses;

    // key = courseID, value = grade
    HashMap<Integer, Integer> grades;

    /**
     * empty constructor for Firebase
     */
    public StudentProfile(){
        // empty constructor for Firebase
    }

    public StudentProfile(String userName, String password, String name, Date birthday,
                          HashMap<String, CourseInterface> enrolledCourses, HashMap<Integer, Integer> grades) {
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

    public HashMap<Integer, Integer> getGrades() {
        return grades;
    }

    @Override
    public HashMap<String, CourseInterface> getEnrolledCourses() {
        if (enrolledCourses == null)
            enrolledCourses = new HashMap<String, CourseInterface>();
        return enrolledCourses;
    }

    @Override
    public int getCourseGrade(int CourseID) {
        return grades.get(CourseID);
    }
}

package com.example.sarah.coursetool.UserProfile;

import com.example.sarah.coursetool.Course.ScheduledCourse;

import java.security.InvalidParameterException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * A class for storing students' personal information and enrollment.
 */
public class StudentProfile implements Profile {
    String userName, password, name;
    Date birthday;
    HashMap<String, ScheduledCourse> enrolledCourses;

    // key = courseID, value = grade
    HashMap<String, Integer> grades;

    /**
     * empty constructor for Firebase
     */
    public StudentProfile(){
        // empty constructor for Firebase
    }

    public StudentProfile(String userName, String password, String name, String birthday) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.enrolledCourses = new HashMap<>();
        this.grades = new HashMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA);
        this.birthday = sdf.parse(birthday, new ParsePosition(0));
    }

    public StudentProfile(String userName, String password, String name, Date birthday,
                          HashMap<String, ScheduledCourse> enrolledCourses, HashMap<String, Integer> grades) {
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

    @Override
    public HashMap<String, Integer> getGrades() {
        return grades;
    }

    @Override
    public HashMap<String, ScheduledCourse> getEnrolledCourses() {
        if (enrolledCourses == null)
            enrolledCourses = new HashMap<>();
        return enrolledCourses;
    }

    @Override
    public void completeCourse(String courseKey, int grade) throws InvalidParameterException {
        // ensure that user is actually enrolled in the course
        boolean enrolledInCourse = false;
        for (String enrolledCourseKey : enrolledCourses.keySet()){
            if (enrolledCourseKey.equals(courseKey)) {
                enrolledInCourse = true;
            }
        }

        if (!enrolledInCourse){
            throw new InvalidParameterException("Student is not enrolled in the course");
        }

        if(grades == null){
            grades = new HashMap<>();
        }

        grades.put(courseKey, grade);
    }

    @Override
    public int getCourseGrade(String courseKey) {
        return grades.getOrDefault(courseKey, -1);
    }


}

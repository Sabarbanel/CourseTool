package com.example.sarah.coursetool.UserProfile;

import com.example.sarah.coursetool.Course.CourseInterface;
import com.example.sarah.coursetool.Course.ScheduledCourse;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Tests the StudentProfile methods
 */
public class StudentProfileUnitTests {
    StudentProfile testStudent;
    HashMap<String, ScheduledCourse> exampleCourses;

    /**
     * Creates a StudentProfile that will be used for tests
     */
    @Before
    public void setup() {
        Date epoch = new Date(0);

        exampleCourses = new HashMap<String, ScheduledCourse>();

        HashMap<String, Integer> grades = new HashMap<>();
        grades.put("CourseA", 50);
        grades.put("CourseB", 90);

        testStudent = new StudentProfile("userName", "password", "Frank Lustre", epoch, exampleCourses, grades);
    }

    /**
     * Tests the getName method
     */
    @Test
    public void getNameTest()
    {
        assertEquals(testStudent.getName(),"Frank Lustre");
    }

    /**
     * Tests the getUserName method
     */
    @Test
    public void getUserNameTest()
    {
        assertEquals(testStudent.getUserName(),"userName");
    }

    /**
     * Tests the getPassword method
     */
    @Test
    public void getPasswordTest()
    {
        assertEquals(testStudent.getPassword(),"password");
    }

    /**
     * Tests the getBirthday method
     */
    @Test
    public void getBirthdayTest()
    {
        assertEquals(testStudent.getBirthday(), new Date(0));
    }

    /**
     * Tests the getCourseGrade method
     */
    @Test
    public void getCourseGradeTest()
    {
        assertEquals(testStudent.getCourseGrade("CourseA"),50);
    }

    /**
     * Tests the getEnrolledCourses method
     */
    @Test
    public void getEnrolledCoursesTest()
    {
        assertEquals(testStudent.getEnrolledCourses(), exampleCourses);
    }

    /**
     * Tests enrolling and completing courses
     */
    @Test
    public void enrollAndCompleteCourseTest() {
        ScheduledCourse course = new ScheduledCourse();
        testStudent.enrolledCourses.put("CourseC", course);
        testStudent.completeCourse("CourseC", 68);
        assertEquals(68, testStudent.getCourseGrade("CourseC"));
    }
}

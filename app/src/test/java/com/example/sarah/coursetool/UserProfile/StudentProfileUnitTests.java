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

        HashMap<Integer, Integer> grades = new HashMap<Integer, Integer>();
        grades.put(134, 50);
        grades.put(135, 90);

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
        assertEquals(testStudent.getCourseGrade(134),50);
    }

    /**
     * Tests the getEnrolledCourses method
     */
    @Test
    public void getEnrolledCoursesTest()
    {
        assertEquals(testStudent.getEnrolledCourses(), exampleCourses);
    }
}

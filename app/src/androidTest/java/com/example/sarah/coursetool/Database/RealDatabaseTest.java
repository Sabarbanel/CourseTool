package com.example.sarah.coursetool.Database;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.sarah.coursetool.Course.CourseInterface;
import com.example.sarah.coursetool.UserProfile.Profile;
import com.example.sarah.coursetool.UserProfile.StudentProfile;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests for the RealDatabaseTest class
 */
@RunWith(AndroidJUnit4.class)
public class RealDatabaseTest {
    RealDatabase database;

    final static String validUsername = "adminTest";
    final static String validPassword = "adminTest";
    final static String invalidUsername = "thisUserDoesNotExist";
    final static String invalidPassword = "PHPisBAD";
    final static int testscheduledCourseID = 1234;

    @Before
    public void setup(){
        database = new RealDatabase();
        database.initDatabase();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests that the getProfileDatabase method returns a UserDatabase when given correct credentials
     */
    @Test
    public void getValidProfileDatabase() {
        UserDatabase loggedInDatabase = database.getProfileDatabase(validUsername, validPassword);
        assertTrue(loggedInDatabase instanceof UserDatabase);
    }

    /**
     * Tests that the getProfileDatabase method throws an exception when given an incorrect password
     */
    @Test(expected = InvalidParameterException.class)
    public void getInvalidProfileDatabase() {
        database.getProfileDatabase(validUsername, invalidPassword);
    }

    /**
     * Tests that getProfileDatabase method throws an exception when given an incorrect username
     */
    @Test(expected = InvalidParameterException.class)
    public void getInvalidUserProfile(){
        database.getProfileDatabase(invalidUsername, validPassword);
    }

    /**
     * Tests the addProfile and getUserProfile methods
     * @Date - July 3, 2018
     */
    @Test
    public void addGetUserProfile() {
        StudentProfile newProfile = new StudentProfile(validUsername, validPassword, "Rob Lowe", new Date());
        database.addProfile(newProfile);
        UserDatabase loggedInDatabase = database.getProfileDatabase(validUsername, validPassword);

        Profile testProfile = loggedInDatabase.getUserProfile();
        assertTrue(testProfile instanceof Profile);
    }

    /**
     * Tests the getScheduledCourses method
     */
    @Test
    public void getScheduledCourses() {
        UserDatabase loggedInDatabase = database.getProfileDatabase(validUsername, validPassword);

        List<CourseInterface> scheduledCourses = loggedInDatabase.getScheduledCourses();
        //assertTrue(scheduledCourses.get(0).getID() == testscheduledCourseID);
    }

    /**
     * Tests the enroll and removeCourse methods
     */
    @Test
    public void enrollAndRemove() {
        UserDatabase loggedInDatabase = database.getProfileDatabase(validUsername, validPassword);
        loggedInDatabase.enroll(testscheduledCourseID);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<CourseInterface> EnrolledCourses = loggedInDatabase.getUserProfile().getEnrolledCourses();

        boolean contains = false;
        /*for (CourseInterface course: EnrolledCourses) {
            if (course.getID() == testscheduledCourseID) {
                contains = true;
                break;
            }
        }*/

        assertTrue(contains);

        loggedInDatabase.removeCourse(testscheduledCourseID);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        EnrolledCourses = loggedInDatabase.getUserProfile().getEnrolledCourses();

        for (CourseInterface course: EnrolledCourses) {
            assertNotEquals(testscheduledCourseID, course.getID());
        }

        loggedInDatabase.enroll(testscheduledCourseID);
    }

    @Test
    public void createAndFetchCourse(){
        database.createCourse("All About Tests", 86, "Dr. X", "TEST101",
                "I will teach you about tests lol", "TEST001, TEST100", "MWF",
                "09:45", "10:45", "01/03/2018", "01/10/2018");
    }

}
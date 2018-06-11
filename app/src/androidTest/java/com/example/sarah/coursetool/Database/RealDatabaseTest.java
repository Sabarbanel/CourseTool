package com.example.sarah.coursetool.Database;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;
import android.test.mock.MockContext;

import com.example.sarah.coursetool.Course.CourseInterface;
import com.example.sarah.coursetool.UserProfile.Profile;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests for the RealDatabaseTest class
 */
@RunWith(AndroidJUnit4.class)
public class RealDatabaseTest {
    RealDatabase database;

    final static String validUsername = "rlowe90";
    final static String validPassword = "lordOfTheRings340";
    final static int testscheduledCourseID = 1234;

    @Before
    public void setup(){
        database = new RealDatabase();
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
     * Tests that the getProfileDatabase method throws an exception when given incorrect credentials
     */
    @Test(expected = InvalidParameterException.class)
    public void getInvalidProfileDatabase() {
        database.getProfileDatabase(validUsername, "PHPisBAD");
    }

    /**
     * Tests the getUserProfile method
     */
    @Test
    public void getUserProfile() {
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

        ArrayList<CourseInterface> scheduledCourses = loggedInDatabase.getScheduledCourses();
        assertTrue(scheduledCourses.get(0).getID() == testscheduledCourseID);
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

        ArrayList<CourseInterface> EnrolledCourses = loggedInDatabase.getUserProfile().getEnrolledCourses();

        boolean contains = false;
        for (CourseInterface course: EnrolledCourses) {
            if (course.getID() == testscheduledCourseID) {
                contains = true;
                break;
            }
        }

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

}
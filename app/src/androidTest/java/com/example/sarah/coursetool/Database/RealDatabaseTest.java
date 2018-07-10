package com.example.sarah.coursetool.Database;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.sarah.coursetool.Course.CourseInterface;
import com.example.sarah.coursetool.Course.ScheduledCourse;
import com.example.sarah.coursetool.UserProfile.Profile;
import com.example.sarah.coursetool.UserProfile.StudentProfile;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static junit.framework.Assert.assertEquals;
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

    @Before
    public void setup(){
        database = new RealDatabase();
        database.initDatabase();

        sleep(1200);
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
        StudentProfile newProfile = new StudentProfile(validUsername, validPassword, "Rob Lowe", "01/12/1994");
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

        HashMap<String, ScheduledCourse> scheduledCourses = loggedInDatabase.getScheduledCourses();
        //assertTrue(scheduledCourses.get(0).getID() == testscheduledCourseID);
    }

    /**
     * Tests the enroll and removeCourse methods
     */
    @Test
    public void enrollAndRemove() {
        UserDatabase loggedInDatabase = database.getProfileDatabase(validUsername, validPassword);

        String key = database.createCourse("All About Tests", 86, "Dr. X", "TEST101",
                "I will teach you about tests lol", "TEST001, TEST100", "MWF",
                "09:45", "10:45", "01/03/2018", "01/10/2018");

        sleep(2000);

        loggedInDatabase.enroll(key);

        sleep(1000);

        HashMap<String, ScheduledCourse> EnrolledCourses = loggedInDatabase.getUserProfile().getEnrolledCourses();
        assertTrue(EnrolledCourses.containsKey(key));
        loggedInDatabase.unenrollFromCourse(key);

        sleep(1000);

        EnrolledCourses = loggedInDatabase.getUserProfile().getEnrolledCourses();
        assertTrue(!EnrolledCourses.containsKey(key));
    }

    @Test
    public void createAndFetchAndDeleteCourse(){
        String key = database.createCourse("All About Tests", 86, "Dr. X", "TEST101",
                "I will teach you about tests lol", "TEST001, TEST100", "MWF",
                "09:45", "10:45", "01/03/2018", "01/10/2018");

        sleep(1000);

        HashMap<String, ScheduledCourse> courses = database.getScheduledCourses();
        ScheduledCourse course = courses.get(key);
        database.removeCourse(key);

        sleep(1000);

        assertEquals(null, database.getScheduledCourses().get(key));

        assertEquals("Wed Jan 03 09:45:00 GMT 2018", course.getStartTimes().get(0).toString());
        assertEquals("Wed Jan 03 10:45:00 GMT 2018", course.getEndTimes().get(0).toString());
        assertEquals(4, course.getStartTimes().size());
    }

    public void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
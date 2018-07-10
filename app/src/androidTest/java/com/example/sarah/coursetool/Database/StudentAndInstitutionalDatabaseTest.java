package com.example.sarah.coursetool.Database;

import android.support.test.runner.AndroidJUnit4;

import com.example.sarah.coursetool.Course.ScheduledCourse;
import com.example.sarah.coursetool.UserProfile.Profile;
import com.example.sarah.coursetool.UserProfile.StudentProfile;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Tests for the StudentDatabase class
 *
 * @author jdeman
 * @author nattwood
 * @date 7/10/2018
 */
@RunWith(AndroidJUnit4.class)
public class StudentAndInstitutionalDatabaseTest {
    UserDatabase userDatabase;
    InstitutionDatabaseInterface instaDatabase;
    final static String validUsername = "adminTest";
    final static String validPassword = "adminTest";
    final static long sleepDur = 1500;

    @Before
    public void setup() throws TimeoutException {
        instaDatabase = new InstitutionDatabase();
        StudentProfile newProfile = new StudentProfile(validUsername, validPassword, "Rob Lowe", "01/12/1994");
        instaDatabase.addProfile(newProfile);

        LoginDatabase LoginDatabase = new LoginDatabase();

        userDatabase = LoginDatabase.getProfileDatabase(validUsername, validPassword);
    }


    /**
     * Tests the getUserProfile methods
     * @Date - July 3, 2018
     */
    @Test
    public void GetUserProfile() throws TimeoutException {
        Profile testProfile = userDatabase.getUserProfile();
        assertTrue(testProfile instanceof Profile);
    }

    /**
     * Tests the getScheduledCourses method
     */
    @Test
    public void getScheduledCourses() throws TimeoutException {

        HashMap<String, ScheduledCourse> scheduledCourses = userDatabase.getScheduledCourses();
        assertTrue(!scheduledCourses.isEmpty());
    }

    /**
     * Tests the enroll and removeCourse methods
     */
    @Test
    public void enrollAndRemove() throws TimeoutException {
        String key = instaDatabase.createCourse("All About Tests", 86, "Dr. X", "TEST101",
                "I will teach you about tests lol", "TEST001, TEST100", "MWF",
                "09:45", "10:45", "01/03/2018", "01/10/2018");

        userDatabase.enroll(key);

        CommonTestUtils.sleep(sleepDur);

        HashMap<String, ScheduledCourse> EnrolledCourses = userDatabase.getUserProfile().getEnrolledCourses();
        assertTrue(EnrolledCourses.containsKey(key));
        userDatabase.unenrollFromCourse(key);

        CommonTestUtils.sleep(sleepDur);

        EnrolledCourses = userDatabase.getUserProfile().getEnrolledCourses();
        assertTrue(!EnrolledCourses.containsKey(key));
    }

    @Test
    public void createAndFetchAndDeleteCourse() throws TimeoutException {
        String key = instaDatabase.createCourse("All About Tests", 86, "Dr. X", "TEST101",
                "I will teach you about tests lol", "TEST001, TEST100", "MWF",
                "09:45", "10:45", "01/03/2018", "01/10/2018");

        CommonTestUtils.sleep(sleepDur);

        HashMap<String, ScheduledCourse> courses = userDatabase.getScheduledCourses();
        ScheduledCourse course = courses.get(key);
        instaDatabase.removeCourse(key);

        CommonTestUtils.sleep(sleepDur);

        assertEquals(null, userDatabase.getScheduledCourses().get(key));

        assertTrue(course.getStartTimes().get(0).toString().startsWith("Wed Jan 03 09:45:00"));
        assertTrue(course.getEndTimes().get(0).toString().startsWith("Wed Jan 03 10:45:00"));
        assertEquals(4, course.getStartTimes().size());
    }
}

package com.example.sarah.coursetool.Database;

import android.support.test.runner.AndroidJUnit4;

import com.example.sarah.coursetool.UserProfile.Profile;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

import static junit.framework.Assert.assertTrue;

/**
 * The file that will be used to test that the drawer has the right username and name
 *
 * @author Sarah Abarbanel
 * @author Dozie
 * @since 2018-07-20
 */
@RunWith(AndroidJUnit4.class)
public class CourseScheduleEspressoTests {
    LoginDatabase database = database = new LoginDatabase();

    final static String validUsername = "adminTest";
    final static String validUsersname = "Rob Lowe";


    @Test
    public void getValidProfileDatabase() throws TimeoutException {
        UserDatabase loggedInDatabase = database.getProfileDatabase("adminTest", "adminTest");
        assertTrue(loggedInDatabase instanceof UserDatabase);
    }


    @Test
    public void userInfoInNavigationDrawer() throws TimeoutException {
        UserDatabase student = database.getProfileDatabase("adminTest", "adminTest");
        Profile stu = student.getUserProfile();
        String username = stu.getUserName();
        String usersname = stu.getName();

        assertTrue(username.equals(validUsername));
        assertTrue(usersname.equals(validUsersname));

    }
}

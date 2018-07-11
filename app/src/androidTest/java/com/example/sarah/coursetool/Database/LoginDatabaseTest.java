package com.example.sarah.coursetool.Database;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.InvalidParameterException;
import java.util.concurrent.TimeoutException;

import static junit.framework.Assert.assertTrue;

/**
 * Tests for the LoginDatabase class
 *
 * @author jdeman
 * @author nattwood
 * @date 7/10/2018
 */
@RunWith(AndroidJUnit4.class)
public class LoginDatabaseTest {
    LoginDatabase database;
    final static String validUsername = "adminTest";
    final static String validPassword = "adminTest";
    final static String invalidUsername = "thisUserDoesNotExist";
    final static String invalidPassword = "PHPisBAD";

    @Before
    public void setup(){
        database = new LoginDatabase();
    }

    /**
     * Tests that the getProfileDatabase method returns a UserDatabase when given correct credentials
     *
     * @author jdeman
     * @author nattwood
     * @date 7/10/2018
     */
    @Test
    public void getValidProfileDatabase() throws TimeoutException {
        UserDatabase loggedInDatabase = database.getProfileDatabase(validUsername, validPassword);
        assertTrue(loggedInDatabase instanceof UserDatabase);
    }

    /**
     * Tests that the getProfileDatabase method throws an exception when given an incorrect password
     *
     * @author jdeman
     * @author nattwood
     * @date 7/10/2018
     */
    @Test(expected = InvalidParameterException.class)
    public void getInvalidProfileDatabase() throws TimeoutException {
        database.getProfileDatabase(validUsername, invalidPassword);
    }

    /**
     * Tests that getProfileDatabase method throws an exception when given an incorrect username
     *
     * @author jdeman
     * @author nattwood
     * @date 7/10/2018
     */
    @Test(expected = InvalidParameterException.class)
    public void getInvalidUserProfile() throws TimeoutException {
        database.getProfileDatabase(invalidUsername, validPassword);
    }
}

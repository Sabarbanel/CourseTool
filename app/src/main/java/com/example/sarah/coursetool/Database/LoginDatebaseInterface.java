package com.example.sarah.coursetool.Database;

import com.example.sarah.coursetool.UserProfile.StudentProfile;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * Interface for a database the allows login
 */
public interface LoginDatebaseInterface {

    /**
     * Returns a userDatabase if one is found
     * @param userName
     * @param password
     * @return UserDatabase
     * @throws InvalidParameterException when the username and password does not match a profile
     */
    UserDatabase getProfileDatabase(String userName, String password) throws InvalidParameterException, TimeoutException;
}

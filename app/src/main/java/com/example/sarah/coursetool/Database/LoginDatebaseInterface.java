package com.example.sarah.coursetool.Database;

import com.example.sarah.coursetool.UserProfile.StudentProfile;

import java.security.InvalidParameterException;
import java.util.Date;

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
    UserDatabase getProfileDatabase(String userName, String password) throws InvalidParameterException;

    /**
     * Creates a profile in the database
     * @param newProfile
     */
    void addProfile(StudentProfile newProfile);
}

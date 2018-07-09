package com.example.sarah.coursetool.Database;

import com.example.sarah.coursetool.UserProfile.StudentProfile;

import java.security.InvalidParameterException;

/**
 * A database access proxy that allows logging in.
 */
public class LoginDatabase implements LoginDatebaseInterface {
    private RealDatabase database;

    /**
     * Constructor
     */
    public LoginDatabase() {
        database = new RealDatabase();
    }

    @Override
    public UserDatabase getProfileDatabase(String userName, String password) throws InvalidParameterException {
        return database.getProfileDatabase(userName, password);
    }

    @Override
    public void addProfile(StudentProfile newProfile) {
        database.addProfile(newProfile);
    }
}

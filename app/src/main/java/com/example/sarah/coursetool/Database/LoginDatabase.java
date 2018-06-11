package com.example.sarah.coursetool.Database;

import java.security.InvalidParameterException;

/**
 * A database access proxy that allows logging in.
 */
public class LoginDatabase implements LoginDatebaseInterface {
    RealDatabase database;

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
}

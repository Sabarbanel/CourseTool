package com.example.sarah.coursetool.Database;

import java.security.InvalidParameterException;
import java.util.concurrent.TimeoutException;

/**
 * A database access proxy that allows logging in.
 */
public class LoginDatabase implements LoginDatebaseInterface {
    private RealDatabase database;

    /**
     * Constructor
     */
    public LoginDatabase() {
        database = RealDatabase.getDatabase();
    }

    @Override
    public UserDatabase getProfileDatabase(String userName, String password) throws InvalidParameterException, TimeoutException {
        return database.getProfileDatabase(userName, password);
    }
}

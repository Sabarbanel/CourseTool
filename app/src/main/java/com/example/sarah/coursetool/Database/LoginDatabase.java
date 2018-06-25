package com.example.sarah.coursetool.Database;

import android.content.Context;

import java.security.InvalidParameterException;

/**
 * A database access proxy that allows logging in.
 */
public class LoginDatabase implements LoginDatebaseInterface {
    private RealDatabase database;

    /**
     * Constructor
     */
    public LoginDatabase(Context context) {
        database = new RealDatabase(context);
    }

    @Override
    public UserDatabase getProfileDatabase(String userName, String password) throws InvalidParameterException {
        return database.getProfileDatabase(userName, password);
    }
}

package com.example.sarah.coursetool.Database;

import android.content.Context;

import java.security.InvalidParameterException;
import java.util.Date;

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
    public void addProfile(String username, String password, String name, Date birthday) {

    }
}

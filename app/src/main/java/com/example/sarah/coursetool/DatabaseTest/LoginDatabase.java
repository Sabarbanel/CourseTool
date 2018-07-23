package com.example.sarah.coursetool.DatabaseTest;

import java.security.InvalidParameterException;
import java.util.concurrent.TimeoutException;

/**
 * A database access proxy that allows logging in.
 */
public class LoginDatabase implements LoginDatabaseInterface {
    private RealDatabase database;

    /**
     * Constructor
     *
     * @author jdeman
     * @author nattwood
     * @date 7/10/2018
     */
    public LoginDatabase() {
        database = RealDatabase.getDatabase();
    }

    @Override
    public UserDatabase getProfileDatabase(String userName, String password) throws InvalidParameterException, TimeoutException {
        return database.getProfileDatabase(userName, password);
    }
}

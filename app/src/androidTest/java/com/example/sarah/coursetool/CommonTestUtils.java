package com.example.sarah.coursetool.DatabaseTest;

/**
 * Common test utility methodes
 *
 * @author jdeman
 * @author nattwood
 * @date 7/10/2018
 */
public class CommonTestUtils {

    /**
     * Sleeps thread
     * @param mills
     * @author jdeman
     * @author nattwood
     * @date 7/10/2018
     */
    public static void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

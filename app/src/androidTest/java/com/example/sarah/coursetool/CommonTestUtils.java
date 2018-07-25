package com.example.sarah.coursetool;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

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

    /**
     * Logs in through UI
     *
     * @Date 23/07/2018
     * @Author jdeman
     * @Author rayub
     */
    public static void login() {
        onView(withId(R.id.username)).perform(typeText("adminTest"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("adminTest"), closeSoftKeyboard());

        onView(withId(R.id.login_button)).perform(click());
    }
}

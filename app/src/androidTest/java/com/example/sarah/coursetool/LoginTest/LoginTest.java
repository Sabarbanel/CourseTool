package com.example.sarah.coursetool.LoginTest;

import org.junit.Rule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.rule.ActivityTestRule;

import com.example.sarah.coursetool.LoginActivity;
import com.example.sarah.coursetool.PasswordChangeActivity;
import com.example.sarah.coursetool.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
/**
 * Tests the login activity
 *
 * @Date 31/07/2018
 * @Author jdeman
 */
public class LoginTest {

    @Rule
    //Access the main activity
    public ActivityTestRule<LoginActivity> mActivityRule  = new ActivityTestRule(LoginActivity.class);

    @Test
    /**
     * Tests that invalid usernames cannot login
     *
     * @Date 31/07/2018
     * @Author jdeman
     */
    public void invalidUsernameTest() {

        onView(withId(R.id.username)).perform(typeText("badUsername"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("password"), closeSoftKeyboard());

        onView(withId(R.id.login_button)).perform(click());

        onView(withText(R.string.invalid_username)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    /**
     * Tests that invalid passwords cannot login
     *
     * @Date 31/07/2018
     * @Author jdeman
     */
    public void invalidPasswordTest() {

        onView(withId(R.id.username)).perform(typeText("AdminTest"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("badpassword"), closeSoftKeyboard());

        onView(withId(R.id.login_button)).perform(click());

        onView(withText(R.string.invalid_password)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    /**
     * Tests that valid credentials can login
     *
     * @Date 31/07/2018
     * @Author jdeman
     */
    public void validLoginTest() {

        onView(withId(R.id.username)).perform(typeText("AdminTest"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("AdminTest"), closeSoftKeyboard());

        onView(withId(R.id.login_button)).perform(click());

        onView(withText("Welcome")).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}
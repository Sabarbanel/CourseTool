package com.example.sarah.coursetool.PasswordChangeTest;

import org.junit.Rule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.rule.ActivityTestRule;

import com.example.sarah.coursetool.Database.LoginDatabase;
import com.example.sarah.coursetool.PasswordChangeActivity;
import com.example.sarah.coursetool.R;

import java.util.concurrent.TimeoutException;

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
 * Tests the password change activity
 *
 * @Date 18/07/2018
 * @Author jdeman
 * @Author rayub
 */
public class PasswordChangeTest {

    @Rule
    //Access the main activity
    public ActivityTestRule<PasswordChangeActivity> mActivityRule  = new ActivityTestRule(PasswordChangeActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
            try {
                new LoginDatabase().getProfileDatabase("adminTest", "adminTest");
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    };

    @Test
    /**
     * Tests that the password cannot be changed if the old password is wrong
     *
     * @Date 18/07/2018
     * @Author jdeman
     * @Author rayub
     */
    public void invalidOldPasswordTest() {

        onView(withId(R.id.oldPassword)).perform(typeText("MakeItSo"), closeSoftKeyboard());
        onView(withId(R.id.newPassword)).perform(typeText("MakeItSo"), closeSoftKeyboard());
        onView(withId(R.id.retypePassword)).perform(typeText("MakeItSo"), closeSoftKeyboard());

        onView(withId(R.id.changePasswordButton)).perform(click());

        onView(withText(R.string.invalid_password)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    /**
     * Tests that the password cannot be changed if the new password and retyped password don't
     * match.
     *
     * @Date 18/07/2018
     * @Author jdeman
     * @Author rayub
     */
    public void notMatchingNewPasswordTest() {

        onView(withId(R.id.oldPassword)).perform(typeText("adminTest"), closeSoftKeyboard());
        onView(withId(R.id.newPassword)).perform(typeText("adminTest"), closeSoftKeyboard());
        onView(withId(R.id.retypePassword)).perform(typeText("MakeItSo"), closeSoftKeyboard());

        onView(withId(R.id.changePasswordButton)).perform(click());

        onView(withText(R.string.not_matching_password)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    /**
     * Tests that the password can be changed
     *
     * @Date 18/07/2018
     * @Author jdeman
     * @Author rayub
     */
    public void validPasswordChangeTest() {

        onView(withId(R.id.oldPassword)).perform(typeText("adminTest"), closeSoftKeyboard());
        onView(withId(R.id.newPassword)).perform(typeText("adminTest"), closeSoftKeyboard());
        onView(withId(R.id.retypePassword)).perform(typeText("adminTest"), closeSoftKeyboard());

        onView(withId(R.id.changePasswordButton)).perform(click());

        assertTrue(mActivityRule.getActivity().isFinishing());
    }
}


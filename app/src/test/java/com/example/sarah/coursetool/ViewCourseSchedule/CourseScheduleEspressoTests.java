package com.example.sarah.coursetool.ViewCourseSchedule;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.sarah.coursetool.BaseNavigationActivity;
import com.example.sarah.coursetool.Database.LoginDatabase;
import com.example.sarah.coursetool.Database.StudentDatabase;
import com.example.sarah.coursetool.LoginActivity;
import com.example.sarah.coursetool.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * The file that will be used to test the UI of checking course schedule
 *
 * @author Sarah Abarbanel
 * @author Lauchlan Toal
 * @since 2018-06-29
 */
@RunWith(AndroidJUnit4.class)
public class CourseScheduleEspressoTests {

    @Rule
    //Access the main activity
    public ActivityTestRule<BaseNavigationActivity> baseNavigationActivity = new ActivityTestRule(BaseNavigationActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
            try {
                new LoginDatabase().getProfileDatabase("AdminTest", "AdminTest");
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    };



    @Test
    public void userInfoInNavigationDrawer() {

        String name, username;
        try {
            StudentDatabase data = new StudentDatabase();
            name = data.getUserProfile().getName();
            username = data.getUserProfile().getUserName();

        } catch (Exception e) {
            name = "";
            username = "";
        }

        onView(withId(R.id.drawername)).check(matches(withText(name)));
        onView(withId(R.id.drawerUsername)).check(matches(withText(username)));


    }
    // test button is clickable onView(withId(R.id.your_button)).check(matches(isClickable()));  (https://stackoverflow.com/questions/32906881/checking-if-a-button-is-clickable-in-espresso-test-android-studio)
    //click button
    //after clicking, check that monday exists, if it doesn't, the button click failed

    //repeat for dropdown and main menu
    //repeat for going from week to day. Query on a specific timeslot

}

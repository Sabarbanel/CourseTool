/**
 * @author: Noah A. and Lauchlan
 * @since: July 23, 2018
 * Tests the completed courses view.
 */

package com.example.sarah.coursetool.Database;

import org.junit.Before;
import org.junit.Rule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.rule.ActivityTestRule;
import android.widget.EditText;
import android.widget.TextView;


import com.example.sarah.coursetool.CompletedCourses.CompletedCoursesActivity;


@RunWith(AndroidJUnit4.class)
public class CompletedCoursesTest {

    @Rule
    //Access the completed courses activity
    public ActivityTestRule<CompletedCoursesActivity> mActivityRule = new ActivityTestRule(CompletedCoursesActivity.class);

    @Test
    /**
     * Tests that courses are loaded
     */
    public void loadCompletedCourses() {
        CompletedCoursesActivity courseList = mActivityRule.getActivity();
    }

}
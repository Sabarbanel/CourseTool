/**
 * @author: Sarah and Lauchlan
 * @since: July 9, 2018
 * Tests the week and day schedule views.
 */

package com.example.sarah.coursetool.ScheduleTest;

import org.junit.Rule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;


import com.example.sarah.coursetool.ViewCourseSchedule.WeekSchedule;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import com.example.sarah.coursetool.R;


@RunWith(AndroidJUnit4.class)
public class ScheduleInstrumentedTest {

    @Rule
    //Access the weekschedule activity
    public ActivityTestRule<WeekSchedule> mActivityRule = new ActivityTestRule(WeekSchedule.class) {
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
     * Tests that clicking next week changes the week.
     *
     */
    public void clickNextWeekTest() {
        WeekSchedule schedule = mActivityRule.getActivity();
        TextView header = (TextView) schedule.findViewById(R.id.weekdatetextview);
        String dateRange = (String) header.getText();
        onView(withId(R.id.nextweek)).perform(click());
        onView(withId(R.id.weekdatetextview)).check(matches(not(withText(dateRange))));
    }

    @Test
    /**
     * Tests that clicking previous week changes the week.
     *
     */
    public void clickNextPrevTest() {
        WeekSchedule schedule = mActivityRule.getActivity();
        TextView header = (TextView) schedule.findViewById(R.id.weekdatetextview);
        String dateRange = (String) header.getText();
        onView(withId(R.id.previousweek)).perform(click());
        onView(withId(R.id.weekdatetextview)).check(matches(not(withText(dateRange))));
    }

    @Test
    /**
     * Tests clicking each day then returning to the week to make sure no errors occur.
     *
     */
    public void clickDays() {
        onView(withId(R.id.monday)).perform(click());
        onView(withId(R.id.retWeek)).perform(click());
        onView(withId(R.id.tuesday)).perform(click());
        onView(withId(R.id.retWeek)).perform(click());
        onView(withId(R.id.wednesday)).perform(click());
        onView(withId(R.id.retWeek)).perform(click());
        onView(withId(R.id.thursday)).perform(click());
        onView(withId(R.id.retWeek)).perform(click());
        onView(withId(R.id.friday)).perform(click());
        onView(withId(R.id.retWeek)).perform(click());
    }

    @Test
    /**
     * Tests that clicking through days to a new week goes to a new week.
     *
     */
    public void clickThroughDays() {
        WeekSchedule schedule = mActivityRule.getActivity();
        TextView header = (TextView) schedule.findViewById(R.id.weekdatetextview);
        String dateRange = (String) header.getText();
        onView(withId(R.id.friday)).perform(click());
        onView(withId(R.id.nextDay)).perform(click());
        onView(withId(R.id.nextDay)).perform(click());
        onView(withId(R.id.retWeek)).perform(click());
        onView(withId(R.id.weekdatetextview)).check(matches(not(withText(dateRange))));
    }


}

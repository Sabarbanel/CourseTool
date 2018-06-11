package com.example.sarah.coursetool.Course;

import com.example.sarah.coursetool.Course.ScheduledCourse;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Tests the methods in the ScheduledCourse class
 */
public class ScheduledCourseUnitTests {
    ScheduledCourse scheduledCourse;
    Date epoch;

    /**
     * Creates a scheduledCourse for testing
     */
    @Before
    public void initializeTestProfile() {
        epoch = new Date(0);

        ArrayList<Integer> preReqs = new ArrayList<Integer>();
        preReqs.add(5);

        ArrayList<Date> startTimeEndTime = new ArrayList<Date>();
        startTimeEndTime.add(epoch);

        scheduledCourse = new ScheduledCourse(134, 456, "Aziz", "CSCI", "Example Description", startTimeEndTime, startTimeEndTime, preReqs);
    }

    /**
     * Tests the getDeptCode method
     */
    @Test
    public void getDeptCodeTest() {
        assertEquals(scheduledCourse.getDeptCode(),"CSCI");
    }

    /**
     * Tests the getDesc method
     */
    @Test
    public void getDescTest() {
        assertEquals(scheduledCourse.getDesc(),"Example Description");
    }

    /**
     * Tests the getEndTimes method
     */
    @Test
    public void getEndTimesTest() {
        assertEquals(scheduledCourse.getEndTimes().get(0),epoch);
    }

    /**
     * Tests the getID method
     */
    @Test
    public void getIDTest() {
        assertEquals(scheduledCourse.getID(),134);
    }

    /**
     * Tests the getPrereqs method
     */
    @Test
    public void getPrereqsTest() {
        assertEquals(scheduledCourse.getPrereqs().get(0).intValue(),5);

    }

    /**
     * Tests the getProf method
     */
    @Test
    public void getProfTest() {
        assertEquals(scheduledCourse.getProf(),"Aziz");
    }

    /**
     * Tests the getRoom method
     */
    @Test
    public void getRoomTest() {
        assertEquals(scheduledCourse.getRoom(),456);
    }

    /**
     * Tests the getStartTimes method
     */
    @Test
    public void getStartTimesTest() {
        assertEquals(scheduledCourse.getStartTimes().get(0),epoch);
    }
}

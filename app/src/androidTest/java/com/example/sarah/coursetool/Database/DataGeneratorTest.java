/**
 * @author: Sarah and Lauchlan
 * @since: July 9, 2018
 * Tests the DataGenerator class and all its methods.
 */
package com.example.sarah.coursetool.Database;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.sarah.coursetool.Course.ScheduledCourse;
import com.example.sarah.coursetool.CourseListing.CourseListing;
import com.example.sarah.coursetool.CourseListing.DataGenerator;
import com.example.sarah.coursetool.Database.RealDatabase;
import com.google.firebase.FirebaseApp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DataGeneratorTest {

    @Test
    public void getGenerator() {
        DataGenerator gen = DataGenerator.getGenerator();
        assertNotNull(gen);
    }

    @Test
    public void getAllCourses() {
        ArrayList<CourseListing> input = new ArrayList<CourseListing>();
        DataGenerator gen = DataGenerator.getGenerator();
        gen.getAllCourses(input);
        assertTrue(input.size() > 0);
    }

    @Test
    public void getFallCourses() {
        ArrayList<CourseListing> input = new ArrayList<CourseListing>();
        DataGenerator gen = DataGenerator.getGenerator();
        gen.getFallCourses(input);
        assertTrue(input.size() > 0);
    }

    @Test
    public void getWinterCourses() {
        ArrayList<CourseListing> input = new ArrayList<CourseListing>();
        DataGenerator gen = DataGenerator.getGenerator();
        gen.getWinterCourses(input);
        assertTrue(input.size() > 0);
    }

    @Test
    public void getSummerCourses() {
        ArrayList<CourseListing> input = new ArrayList<CourseListing>();
        DataGenerator gen = DataGenerator.getGenerator();
        gen.getSummerCourses(input);
        assertTrue(input.size() > 0);
    }

    @Test
    public void getCSCICourses() {
        ArrayList<CourseListing> input = new ArrayList<CourseListing>();
        DataGenerator gen = DataGenerator.getGenerator();
        gen.getCSCICourses(input);
        assertTrue(input.size() > 0);
    }

    @Test
    public void getDaySchedule() {
        ArrayList<CourseListing> input = new ArrayList<CourseListing>();
        DataGenerator gen = DataGenerator.getGenerator();
        Calendar cal = Calendar.getInstance();
        gen.getDaySchedule(cal, input);
    }
}
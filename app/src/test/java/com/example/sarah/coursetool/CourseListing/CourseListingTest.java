/**
 * @author Lauchlan Toal
 * @author Noah Sealy
 * Tests for the courselisting object.
 */

package com.example.sarah.coursetool.CourseListing;

import com.example.sarah.coursetool.CourseListing.CourseListing;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import static org.junit.Assert.assertEquals;

public class CourseListingTest {

    CourseListing course;
    String title = "Intro to testing";
    String professor = "Dr. Tester";
    String department = "CSCI2211";
    String description = "How to test code";
    Date start = new Date();
    Date end = new Date();
    int id = 21;
    int room = 532;
    ArrayList<String> prerequisites = new ArrayList<String>();
    String prerequisite = "CSCI1211";


    @Before
    public void createCourseListing() {
        prerequisites.add(prerequisite);
        course = new CourseListing(title, professor,department, description, start, end, id, room, prerequisites);
    }

    @Test
    public void checkTitle() {
        assertEquals(course.courseTitle, title);
    }

    @Test
    public void checkProfessor() {
        assertEquals(course.courseProf, professor);
    }

    @Test
    public void checkDepartment() {
        assertEquals(course.courseDepartment, department);
    }

    @Test
    public void checkDescription() {
        assertEquals(course.courseDescription, description);
    }

    @Test
    public void checkStart() {
        assertEquals(course.courseStartTime, start);
    }

    @Test
    public void checkEnd() {
        assertEquals(course.courseEndTime, end);
    }

    @Test
    public void checkId() {
        assertEquals(course.courseID, id);
    }

    @Test
    public void checkRoom() {
        assertEquals(course.courseRoom, room);
    }

    @Test
    public void checkPrerequisites() {
        assertEquals(course.coursePreqs.get(0), prerequisite);
    }

}

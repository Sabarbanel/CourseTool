package com.example.sarah.coursetool;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sarah.coursetool.Course.CourseInterface;
import com.example.sarah.coursetool.Database.LoginDatabase;
import com.example.sarah.coursetool.Database.StudentDatabase;
import com.example.sarah.coursetool.Database.UserDatabase;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class RegisterActivity extends BaseNavigationActivity {

    StudentDatabase studentDatabase;
    LoginDatabase loginDatabase;
    UserDatabase userDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        loginDatabase = new LoginDatabase(this);
        //get the user database for the user currently logged in
        userDatabase = loginDatabase.getProfileDatabase("rlowe90", "lordOfTheRings340");
    }
    /*
    Called when 'Add Course' button is pressed
     */
    public void addCourse(View v){
        Button addButton = findViewById(R.id.addCourse);
        EditText codeField = findViewById(R.id.courseCode);
        String c = codeField.getText().toString();
        //convert the user entry into an int
        int code = Integer.parseInt(c);
        //check if the course entered by the user is in their list of courses already by ID
        ArrayList<CourseInterface>courses = userDatabase.getScheduledCourses();
        CourseInterface scheduledCourse = null;
        for(CourseInterface course : courses){
            if(course.getID() == code){
                scheduledCourse = course;
            }
            else{
                scheduledCourse = null;
            }
        }
        //check to see if text field contains content
        if (noConflict(courses, scheduledCourse)) {
            if (courses.contains(scheduledCourse)) {
                try {
                    userDatabase.enroll(code);
                    Toast toast = Toast.makeText(getApplicationContext(), "Course added successfully!", Toast.LENGTH_SHORT);
                } catch (InvalidParameterException ipe) {
                    Toast toast = Toast.makeText(getApplicationContext(), ipe.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        }
    }
    /**
     * This method will be used for checking if there are any conflicts in the students schedule while enrolling in new courses
     * This will be implemented as a condition that must pass before a course can be added to a students course list
     * Written by NS and RA on July 10th 2018
     */
    public boolean noConflict(ArrayList<CourseInterface> schedule, CourseInterface newCourse) {
        for (int i = 0; i < schedule.size(); i++) {
            if(newCourse.getStartTimes().get(0).before(schedule.get(i).getStartTimes().get(0))
                    && newCourse.getEndTimes().get(0).after(schedule.get(i).getEndTimes().get(0))) {
                return false;
            } else if (schedule.get(i).getStartTimes().get(0).before(newCourse.getStartTimes().get(0))
                    && schedule.get(i).getEndTimes().get(0).after(newCourse.getStartTimes().get(0))) {
                return false;
            } else if (schedule.get(i).getEndTimes().get(0).after(newCourse.getStartTimes().get(0))
                    && schedule.get(i).getStartTimes().get(0).before(newCourse.getStartTimes().get(0))) {
                return false;
            } else if (newCourse.getEndTimes().get(0).after(schedule.get(i).getStartTimes().get(0))
                    && newCourse.getStartTimes().get(0).before(schedule.get(i).getStartTimes().get(0))) {
                return false;
            }
        }
        return true;
    }
}

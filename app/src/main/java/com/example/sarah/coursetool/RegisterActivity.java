package com.example.sarah.coursetool;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sarah.coursetool.Course.CourseInterface;
import com.example.sarah.coursetool.Course.ScheduledCourse;
import com.example.sarah.coursetool.Database.LoginDatabase;
import com.example.sarah.coursetool.Database.StudentDatabase;
import com.example.sarah.coursetool.Database.UserDatabase;
import com.example.sarah.coursetool.UserProfile.Profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class RegisterActivity extends BaseNavigationActivity {

    StudentDatabase studentDatabase;
    LoginDatabase loginDatabase;
    UserDatabase userDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        loginDatabase = new LoginDatabase();
        Profile profile;
        //get the user database for the user currently logged in

        try{
            userDatabase = loginDatabase.getProfileDatabase("adminTest", "adminTest");
        }
        catch(TimeoutException e){
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    /*
    Called when 'Add Course' button is pressed
     */
    public void addCourse(View v){
        Button addButton = findViewById(R.id.addCourse);
        EditText codeField = findViewById(R.id.courseCode);
        String id = codeField.getText().toString();
        Profile profile = null;
        try{
            profile = userDatabase.getUserProfile();
        }
        catch(TimeoutException e){
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }

        //convert the user entry into an int
        //int code = Integer.parseInt(c);
        //check if the course entered by the user is in their list of courses already by ID
        int exists = 0;
        String keyId = null;
        while(exists==0){
            HashMap<String, ScheduledCourse> courses = profile.getEnrolledCourses();
            ScheduledCourse scheduledCourse = null;
            for(Map.Entry<String, ScheduledCourse> entry : courses.entrySet()){
                ScheduledCourse value = entry.getValue();
                String key = entry.getKey();
                ScheduledCourse temp = null;
                //course already exists
                if(value.getID() == id) {
                    scheduledCourse = value;
                    keyId = key;
                    exists = 1;
                }
            }
        }
        if (exists == 0){

            try {
                userDatabase.enroll(keyId);
                Toast toast = Toast.makeText(getApplicationContext(), "Course added successfully!", Toast.LENGTH_SHORT);
            } catch (TimeoutException e) {
                Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        else{
            //could not enroll
            Toast toast = Toast.makeText(getApplicationContext(), "You are already enrolled in this course!", Toast.LENGTH_SHORT);
            toast.show();
        }

        /*check to see if there is time conflict between the course being added and the
        courses already int the user's list of courses*/
        /*if (noConflict(courses, scheduledCourse)) {
            if (courses.contains(scheduledCourse)) {
                try {
                    userDatabase.enroll(code);
                    Toast toast = Toast.makeText(getApplicationContext(), "Course added successfully!", Toast.LENGTH_SHORT);
                } catch (InvalidParameterException ipe) {
                    Toast toast = Toast.makeText(getApplicationContext(), ipe.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        }*/
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

package com.example.sarah.coursetool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CourseCreationActivity extends AppCompatActivity {

    private EditText courseName, capacity, professor, deptCode, description, prerequisites,
            daysOfWeek, startTime, endTime, startDate, endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_creation);

        courseName = findViewById(R.id.courseName);
        capacity = findViewById(R.id.capacity);
        professor = findViewById(R.id.professor);
        deptCode = findViewById(R.id.deptCode);
        description = findViewById(R.id.description);
        daysOfWeek = findViewById(R.id.daysOfWeek);
        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);
        prerequisites = findViewById(R.id.prerequisites);

        //DatePickerTextField.configureTextField(startDate, CourseCreationActivity.this);
        new DatePickerTextField().configureTextField(startDate, CourseCreationActivity.this);
        new DatePickerTextField().configureTextField(endDate, CourseCreationActivity.this);
    }

    public void onClickCreateCourse(View view){
        // get vals and create course
        // pull from remote branch and get singleton
    }
}

package com.example.sarah.coursetool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class CourseCreationActivity extends AppCompatActivity {

    private EditText startDate, endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_creation);

        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);

        //DatePickerTextField.configureTextField(startDate, CourseCreationActivity.this);
        new DatePickerTextField().configureTextField(startDate, CourseCreationActivity.this);
        new DatePickerTextField().configureTextField(endDate, CourseCreationActivity.this);
    }
}

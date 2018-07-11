package com.example.sarah.coursetool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.sarah.coursetool.Database.InstitutionDatabase;

/**
 * Activity that allows administrators to create new courses that will be added to the database.
 *
 * @author jdeman
 * @author nattwood
 * @date 7/10/2018
 */
public class CourseCreationActivity extends AppCompatActivity {

    private EditText courseNameTextField, capacityTextField, professorTextField, deptCodeTextField,
            descriptionTextField, prerequisitesTextField, daysOfWeekTextField, startTimeTextField,
            endTimeTextField, startDateTextField, endDateTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_creation);

        courseNameTextField = findViewById(R.id.courseName);
        capacityTextField = findViewById(R.id.capacity);
        professorTextField = findViewById(R.id.professor);
        deptCodeTextField = findViewById(R.id.deptCode);
        descriptionTextField = findViewById(R.id.description);
        daysOfWeekTextField = findViewById(R.id.daysOfWeek);
        startTimeTextField = findViewById(R.id.startTime);
        endTimeTextField = findViewById(R.id.endTime);
        startDateTextField = findViewById(R.id.startDate);
        endDateTextField = findViewById(R.id.endDate);
        prerequisitesTextField = findViewById(R.id.prerequisites);

        //DatePickerTextField.configureTextField(startDateTextField, CourseCreationActivity.this);
        new DatePickerTextField().configureTextField(startDateTextField, CourseCreationActivity.this);
        new DatePickerTextField().configureTextField(endDateTextField, CourseCreationActivity.this);
    }

    /**
     * Event handler for creating a course
     * @param view
     *
     * @author jdeman
     * @author nattwood
     * @date 7/10/2018
     */
    public void onClickCreateCourse(View view){
        InstitutionDatabase instDatabase = new InstitutionDatabase();

        instDatabase.createCourse(courseNameTextField.getText().toString(),
                Integer.valueOf(capacityTextField.getText().toString()),
                professorTextField.getText().toString(),
                deptCodeTextField.getText().toString(),
                descriptionTextField.getText().toString(),
                prerequisitesTextField.getText().toString(),
                daysOfWeekTextField.getText().toString(),
                startTimeTextField.getText().toString(),
                endTimeTextField.getText().toString(),
                startDateTextField.getText().toString(),
                endDateTextField.getText().toString());

        finish();
    }
}

/**
 * @author Noah Attwood
 * @author Lauchlan Toal
 * @since 2018-07-18
 * Activity that allows for a grade to be assigned to a student.
 */

package com.example.sarah.coursetool.CourseListing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sarah.coursetool.BaseNavigationActivity;
import com.example.sarah.coursetool.Database.InstitutionDatabase;
import com.example.sarah.coursetool.R;

import java.security.InvalidParameterException;

public class AssignGradesActivity extends BaseNavigationActivity {

    private String courseKey;
    private InstitutionDatabase institutionDatabase;
    private EditText userNameTextField, gradeTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_grades);

        institutionDatabase = new InstitutionDatabase();

        userNameTextField = findViewById(R.id.studentUserName);
        gradeTextField = findViewById(R.id.grade);

        // get the intent that was used to switch to this activity and get the course's DB key
        Intent prevIntent = getIntent();
        courseKey = prevIntent.getStringExtra("CourseKey");
    }

    public void onClickAssignGradeToStudent(View view){
        if(view != null) {
            // close the keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        String username = userNameTextField.getText().toString();
        int grade = Integer.valueOf(gradeTextField.getText().toString());

        try {
            institutionDatabase.assignGradeToStudent(username, courseKey, grade);
        } catch (InvalidParameterException ipe){
            Toast toast = Toast.makeText(getApplicationContext(), ipe.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }

        userNameTextField.setText("");
        gradeTextField.setText("");
    }

}

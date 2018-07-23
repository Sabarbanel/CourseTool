package com.example.sarah.coursetool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.sarah.coursetool.DatabaseTest.InstitutionDatabase;
import com.example.sarah.coursetool.DatabaseTest.InstitutionDatabaseInterface;
import com.example.sarah.coursetool.UserProfile.StudentProfile;

/**
 * An Activity that allows new users to sign up to use the app.
 *
 * @author jdeman
 * @author nattwood
 * @date 7/10/2018
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText usernameTextField, passwordTextField, nameTextField, birthdayTextField;
    private InstitutionDatabaseInterface database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = new InstitutionDatabase();

        setContentView(R.layout.activity_register);
        usernameTextField = findViewById(R.id.username);
        passwordTextField = findViewById(R.id.password);
        nameTextField = findViewById(R.id.name);
        birthdayTextField = findViewById(R.id.birthday);

        new DatePickerTextField().configureTextField(birthdayTextField, RegisterActivity.this);
    }

    /**
     * Event handler for creating a new profile
     * @param view
     *
     * @author jdeman
     * @author nattwood
     * @date 7/10/2018
     */
    public void onClickRegisterStudent(View view){
        StudentProfile newProfile = new StudentProfile(usernameTextField.getText().toString(),
                passwordTextField.getText().toString(),
                nameTextField.getText().toString(),
                birthdayTextField.getText().toString());
        database.addProfile(newProfile);
        finish();
    }

}

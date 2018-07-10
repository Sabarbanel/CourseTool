package com.example.sarah.coursetool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.sarah.coursetool.Database.InstitutionDatabase;

/**
 * An Activity that allows new users to sign up to use the app.
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText usernameTextField, passwordTextField, nameTextField, birthdayTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameTextField = findViewById(R.id.username);
        passwordTextField = findViewById(R.id.password);
        nameTextField = findViewById(R.id.name);
        birthdayTextField = findViewById(R.id.birthday);

        new DatePickerTextField().configureTextField(birthdayTextField, RegisterActivity.this);
    }

    public void onClickRegisterStudent(View view){

    }

}

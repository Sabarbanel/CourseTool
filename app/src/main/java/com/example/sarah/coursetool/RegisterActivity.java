package com.example.sarah.coursetool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

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


}

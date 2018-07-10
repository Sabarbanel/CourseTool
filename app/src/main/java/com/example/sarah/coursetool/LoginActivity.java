package com.example.sarah.coursetool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sarah.coursetool.Database.LoginDatabase;
import com.example.sarah.coursetool.Database.RealDatabase;
import com.example.sarah.coursetool.Database.UserDatabase;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An Activity for logging in to the application
 */
public class LoginActivity extends AppCompatActivity {

    LoginDatabase loginDatabase;
    RealDatabase appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginDatabase = new LoginDatabase();
        appState = ((RealDatabase) getApplicationContext());
    }

    /**
     * Called when the login button is pressed
     */
    public void processLogin(View view){
        EditText usernameTextBox = findViewById(R.id.username);
        EditText passwordTextBox = findViewById(R.id.password);

        if(view != null) {
            // close the keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        UserDatabase userDatabase = null;

        try{
            userDatabase = loginDatabase.getProfileDatabase(usernameTextBox.getText().toString(),
                    passwordTextBox.getText().toString());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } catch (InvalidParameterException ipe) {
            // username/password was wrong - Show an error message
            passwordTextBox.setText("");
            Toast toast = Toast.makeText(getApplicationContext(), ipe.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onCreateClassClick(View view){
        Intent intent = new Intent(this, CourseCreationActivity.class);
        startActivity(intent);
    }

    public void onRegisterClick(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}

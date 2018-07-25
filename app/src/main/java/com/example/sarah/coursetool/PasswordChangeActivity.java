package com.example.sarah.coursetool;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sarah.coursetool.Database.StudentDatabase;
import com.example.sarah.coursetool.Database.UserDatabase;

import java.util.concurrent.TimeoutException;

public class PasswordChangeActivity extends AppCompatActivity {

    UserDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);

        database = new StudentDatabase();
    }

    /**
     * changes the users password
     * @param view
     *
     * @Date 23/07/2018
     * @Author jdeman
     * @Author rayub
     */
    public void onChangePassword(View view) {
        EditText oldPassword = findViewById(R.id.oldPassword);
        EditText newPassword = findViewById(R.id.newPassword);
        EditText retypePassword = findViewById(R.id.retypePassword);

        if(view != null) {
            // close the keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        try {
            if (!oldPassword.getText().toString().equals(database.getUserProfile().getPassword())) {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.invalid_password, Toast.LENGTH_SHORT);
                toast.show();
            } else if (!newPassword.getText().toString().equals(retypePassword.getText().toString())) {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.not_matching_password, Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.password_changed, Toast.LENGTH_SHORT);
                toast.show();
                database.changePassword(newPassword.getText().toString());
                this.finish();
            }
        } catch (TimeoutException e) {
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}

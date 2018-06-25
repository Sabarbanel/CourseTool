package com.example.sarah.coursetool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * Called when the login button is pressed
     */
    public void processLogin(View view){
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        if(view != null) {
            // close the keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else{
            // username/password was wrong - Show an error message
            password.setText("");
            CharSequence errorText = getString(R.string.invalid_username_password);
            Toast toast = Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}

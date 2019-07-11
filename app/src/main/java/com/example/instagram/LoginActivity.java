package com.example.instagram;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private Button  loginBtn;
    private Button signBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hooking up logic to login
        usernameInput = findViewById(R.id.etUsername);
        passwordInput = findViewById(R.id.etPassword);
        loginBtn = findViewById(R.id.btnLogin);
        signBtn = findViewById(R.id.btnSign);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = usernameInput.getText().toString();
                final String password = passwordInput.getText().toString();

                login(username, password);
            }
        });

        ParseUser currentUser = ParseUser.getCurrentUser();

        final String password = passwordInput.getText().toString();
        if (currentUser != null) {
            // do stuff with the user
            Log.d("LoginActivity", "Success");
            final Intent intent = new Intent(LoginActivity.this, ComposeActivity.class);
            startActivity(intent);
            finish();

        }

            signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser user = new ParseUser();

                final String username = usernameInput.getText().toString();
                final String password = passwordInput.getText().toString();

                user.setUsername(username);
                user.setPassword(password);


                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.d("SignUpActivity", "Sign Up Success");
                            final Intent intent = new Intent(LoginActivity.this, ComposeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });



    }

    private void login(String username, String password){
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null){
                    Log.d("LoginActivity", "Login Successful");
                    final Intent intent = new Intent(LoginActivity.this, ComposeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                   Log.e("LoginActivity", "Login Failure");
                   e.printStackTrace();
                }
            }
        });
    }
}

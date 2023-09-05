package com.example.two_tables;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {

    private Button loginButton_loginpage, signUpButton_loginpage;
    private String EmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private EditText emailInput, passwordInput;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        loginButton_loginpage = (Button) findViewById(R.id.loginButton_loginpage);
        signUpButton_loginpage = (Button)findViewById(R.id.signUpButton_loginpage);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwdInput);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        SharedPreferences shared = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = shared.getString("email", "");
        String s2 = shared.getString("password", "");

        emailInput.setText(s1);
        passwordInput.setText(s2);


        loginButton_loginpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 email = emailInput.getText().toString();
                password = passwordInput.getText().toString();


                SharedPreferences shared = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("email",emailInput.getText().toString());
                editor.putString("password", passwordInput.getText().toString());
                editor.commit();

                if (TextUtils.isEmpty(email) || !(email.matches(EmailPattern))) {
                    emailInput.setError("Email should be correct and not empty");
                } else if (TextUtils.isEmpty(password) || password.length()<8) {
                    passwordInput.setError("Password must not be longer than 8 characters");
                }else{
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(loginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(loginActivity.this, "Error! Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        signUpButton_loginpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });




    }
}
package com.example.two_tables;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class SignUpActivity extends AppCompatActivity {

    private EditText email_input, passwd_input, Cpassword_input;
    private Button registrationSignUpButton, loginButton;
    private String username, email, password , Cpassword;
    private String EmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        email_input = findViewById(R.id.email_input);
        passwd_input = findViewById(R.id.passwd_input);
        Cpassword_input  = findViewById(R.id.Cpasswd_input);
        registrationSignUpButton = findViewById(R.id.registrationSignUpButton);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        registrationSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = email_input.getText().toString();
                password = passwd_input.getText().toString();
                Cpassword = Cpassword_input.getText().toString();


                if (TextUtils.isEmpty(email) || !(email.matches(EmailPattern))) {
                    email_input.setError("Email should be correct and not empty");
                } else if (TextUtils.isEmpty(password) || password.length()<8) {
                    passwd_input.setError("Password must not be longer than 8 characters");
                }else if(TextUtils.isEmpty(Cpassword) || !Cpassword.matches(password)){
                    passwd_input.setError("Please confirm your password");
                }else if(!(email.matches(EmailPattern))){

                }
                else {
                    addDataToFirestore(username, email, password);
                }
            }
        });
    }

    private void addDataToFirestore(String username, String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    loginButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(SignUpActivity.this, loginActivity.class);
                            startActivity(intent);
                        }
                    });
                }else{
                    Toast.makeText(SignUpActivity.this, "Error! Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
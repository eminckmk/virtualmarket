package com.example.veritabaniodevi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button mLoginBtn,mRegisterBtn;
    FirebaseAuth fAuth;
    CheckBox showpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        showpassword=findViewById(R.id.showpassword);
        mEmail= findViewById(R.id.text_loginmail);
        mPassword= findViewById(R.id.text_loginpassword);
        mRegisterBtn= findViewById(R.id.button_signup);
        mLoginBtn= findViewById(R.id.button_login);
        fAuth = FirebaseAuth.getInstance();

        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    mPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showpassword.setButtonDrawable(R.drawable.hideicon);
                }
                else{
                    mPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showpassword.setButtonDrawable(R.drawable.showicon);
                }}

        });

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Signup.class);
                startActivity(intent);
                finish();
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (email.equals("")) {

                    Intent intent = new Intent(Login.this, AdminHomePage.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    try {

                        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(Login.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login.this, UserCategoryPage.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(Login.this, "Username or Password is Wrong", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } catch (Exception ea) {
                        Toast.makeText(Login.this, "username or Password cannot be lank", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
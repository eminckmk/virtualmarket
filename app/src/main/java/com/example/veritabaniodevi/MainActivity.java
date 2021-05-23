package com.example.veritabaniodevi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button button_login;
    Button button_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button_login=findViewById(R.id.button_login);
        button_signup=findViewById(R.id.button_signup);



        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gologin = new Intent(MainActivity.this, Login.class);
                startActivity(gologin);

            }
        });


        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gosignup = new Intent(MainActivity.this,Signup.class);

                startActivity(gosignup);
                //FirebaseAuth.getInstance().signOut();
            }
        });
    }
}
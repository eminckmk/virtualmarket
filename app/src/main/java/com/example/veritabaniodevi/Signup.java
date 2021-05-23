package com.example.veritabaniodevi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    EditText textName,textMail,textPassword,textdate;
    Button buttonSignUp,buttonLogIn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    CheckBox showpassword22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        textMail=  (findViewById(R.id.text_mail));
        textName=  (findViewById(R.id.text_name));
        textPassword=  (findViewById(R.id.text_password));
        textdate= (findViewById(R.id.text_date));
        buttonSignUp =findViewById(R.id.button_signup2);
        buttonLogIn = findViewById(R.id.button_login2);
        showpassword22 = findViewById(R.id.showpassword2);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        showpassword22.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    textPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showpassword22.setButtonDrawable(R.drawable.hideicon);
                }
                else{
                    textPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showpassword22.setButtonDrawable(R.drawable.showicon);
                }}

        });

        textdate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog;
                datePickerDialog = new DatePickerDialog(Signup.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textdate.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },year,month,day);

                datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",datePickerDialog);
                datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"Cancel",datePickerDialog);
                datePickerDialog.show();
            }
        });

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this,Login.class);
                startActivity(intent);
                finish();
            }
        });


        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String email = textMail.getText().toString().trim();
                    String name = textName.getText().toString().trim();
                    String password = textPassword.getText().toString().trim();
                    String date = textdate.getText().toString().trim();

                    if (TextUtils.isEmpty(email) || TextUtils.isEmpty(name) || TextUtils.isEmpty(password) || TextUtils.isEmpty(date)) {

                        textMail.setError("Username or Password cannot be lank");
                        textPassword.setError("Username or Password cannot be lank");

                    }

                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(Signup.this, "User Created", Toast.LENGTH_LONG).show();

                                userID = fAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fStore.collection("users").document(userID);
                                Map<String, Object> user = new HashMap<>();
                                user.put("name", name);
                                user.put("email", email);
                                user.put("date", date);

                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        Log.e("onSuccess: ", userID);

                                    }
                                });


                            } else {
                                Toast.makeText(Signup.this,  task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }


                        }
                    });

             /*   FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Kullanici Bilgileri");

                UserInformation userInformation = new UserInformation(1,textName.getText().toString(),textMail.getText().toString(),textPassword.getText().toString(),textdate.getText().toString());
                myRef.push().setValue(userInformation);*/


                }catch (Exception ea){
                    Toast.makeText(Signup.this,"Username or Password cannot be lank",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
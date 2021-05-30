package com.example.veritabaniodevi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class UserSettings extends AppCompatActivity {

    private EditText textPersonName;
    private EditText textPersonMail;
    private EditText textPersonDate;
    private ImageView imagePersonImage;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FirebaseAuth fAuth;
    private String userID;
    private Button buttonSave;
    private CheckBox checkBox;
    private Toolbar toolbarUserSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        textPersonDate = findViewById(R.id.textPersonDate);
        textPersonName = findViewById(R.id.textPersonName);
        textPersonMail = findViewById(R.id.textPersonMail);
        imagePersonImage = findViewById(R.id.imagePersonImage);
        imagePersonImage.setImageResource(R.drawable.usericon);
        toolbarUserSettings = findViewById(R.id.toolbarUserSettings);
        buttonSave = findViewById(R.id.buttonSave);
        checkBox = findViewById(R.id.checkBox);
        fAuth = FirebaseAuth.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        toolbarUserSettings.setTitle("User Settings");
        toolbarUserSettings.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbarUserSettings.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    textPersonMail.setEnabled(true);
                    textPersonName.setEnabled(true);
                    textPersonDate.setEnabled(true);
                    buttonSave.setVisibility(View.VISIBLE);
                }
                else{
                    textPersonDate.setEnabled(false);
                    textPersonName.setEnabled(false);
                    textPersonMail.setEnabled(false);
                    buttonSave.setVisibility(View.GONE);
                }
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseFirestore.collection("users").document(userID).update("name",String.valueOf(textPersonName.getText()));
                firebaseFirestore.collection("users").document(userID).update("email",String.valueOf(textPersonMail.getText()));
                firebaseFirestore.collection("users").document(userID).update("date",String.valueOf(textPersonDate.getText()));

            }
        });







        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    textPersonName.setText(documentSnapshot.get("name").toString());
                    textPersonMail.setText(documentSnapshot.get("email").toString());
                    textPersonDate.setText(documentSnapshot.get("date").toString());

                }
            }
        });









        }



    }


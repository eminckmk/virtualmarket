package com.example.veritabaniodevi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class AdminProductAdd extends AppCompatActivity {

    private ArrayList<String> categorys ;
    private ArrayAdapter<String> categoryAdapter;
    private Spinner spinnerCategory;
    ArrayList<String> userCommentFromFB;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product_add);

        spinnerCategory = findViewById(R.id.spinnerCategory);

        categorys.add("Test");
        categorys.add("test2");

        userCommentFromFB = new ArrayList<>();

        categoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,categorys);

        firebaseFirestore = FirebaseFirestore.getInstance();
        spinnerCategory.setAdapter(categoryAdapter);


        getDataFromFirestore();

       /* spinnerCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/




    }

    public void getDataFromFirestore() {

        CollectionReference collectionReference = firebaseFirestore.collection("Category");

        collectionReference.orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {


                if (queryDocumentSnapshots != null) {

                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {

                        Map<String,Object> data = snapshot.getData();

                        //Casting
                        String comment = (String) data.get("comment");

                        userCommentFromFB.add(comment);

                    }
                }
            }
        });

    }
}
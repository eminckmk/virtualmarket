package com.example.veritabaniodevi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class UserCategoryPage extends AppCompatActivity {

    ArrayList<String> userEmailFromFB;
    ArrayList<String> userCommentFromFB;
    ArrayList<String> userImageFromFB;
    private  FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference categoryRef = db.collection("Category");
    private  UserCategoryAdapter adapterCategory;
    private FloatingActionButton fabBasket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        userCommentFromFB = new ArrayList<>();
        userEmailFromFB = new ArrayList<>();
        userImageFromFB = new ArrayList<>();

        getDataFromFirestore();
        setUpRecyclerview();





    }

    private void  setUpRecyclerview(){
        Query query = categoryRef.orderBy("comment",Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Categorys> options = new FirestoreRecyclerOptions.Builder<Categorys>().setQuery(query,Categorys.class).build();

        adapterCategory = new UserCategoryAdapter(options,userCommentFromFB,userImageFromFB,this);

        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapterCategory);

    }
    @Override
    protected void onStart() {
        super.onStart();
        adapterCategory.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapterCategory.stopListening();
    }

    public void getDataFromFirestore() {

        CollectionReference collectionReference = db.collection("Category");

        collectionReference.orderBy("comment", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    Toast.makeText(UserCategoryPage.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                }

                if (queryDocumentSnapshots != null) {

                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {

                        Map<String, Object> data = snapshot.getData();

                        //Casting
                        String comment = (String) data.get("comment");
                        String downloadUrl = (String) data.get("downloadurl");

                        userCommentFromFB.add(comment);
                        userImageFromFB.add(downloadUrl);

                        try {
                            adapterCategory.notifyDataSetChanged();
                        } catch (Exception ea) {

                        }


                    }


                }


            }
        });
    }




}
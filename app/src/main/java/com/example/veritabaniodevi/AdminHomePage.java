package com.example.veritabaniodevi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
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


public class AdminHomePage extends AppCompatActivity {

    ArrayList<String> userEmailFromFB;
    ArrayList<String> userCommentFromFB;
    ArrayList<String> userImageFromFB;
    FirestoreRecyclerOptions<Categorys> test;
    Context context;
    private TextView textToolbarAdminCategory;
    private Toolbar toolbar;
    private  FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference categoryRef = db.collection("Category");
    private AdminCategoryAdapter adminCategoryAdapter;
    FloatingActionButton fabCategoryAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        textToolbarAdminCategory = findViewById(R.id.textToolbarAdminCategory);
        textToolbarAdminCategory.setText("Admin Category");
        textToolbarAdminCategory.setGravity(View.TEXT_ALIGNMENT_CENTER);
        toolbar = (Toolbar) findViewById(R.id.toolbarAdminCategoryy);
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);

        userCommentFromFB = new ArrayList<>();
        userEmailFromFB = new ArrayList<>();
        userImageFromFB = new ArrayList<>();


        getDataFromFirestore();
        setUpRecyclerview();

        fabCategoryAdd = findViewById(R.id.fabCategoryAdd);
        fabCategoryAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomePage.this,AdminCategoryAdd.class);
                startActivity(intent);
            }
        });

    }

    private void  setUpRecyclerview(){

        Query query = categoryRef.orderBy("comment",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Categorys> options = new FirestoreRecyclerOptions.Builder<Categorys>().setQuery(query,Categorys.class).build();
        adminCategoryAdapter = new AdminCategoryAdapter(options,userCommentFromFB,userImageFromFB,this);
        RecyclerView recyclerView = findViewById(R.id.rv2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adminCategoryAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adminCategoryAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adminCategoryAdapter.stopListening();
    }


    public void getDataFromFirestore() {

        CollectionReference collectionReference = db.collection("Category");

        collectionReference.orderBy("comment", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    Toast.makeText(AdminHomePage.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
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
                            adminCategoryAdapter.notifyDataSetChanged();
                        } catch (Exception ea) {

                        }


                    }


                }


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbarmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemReflesh:
                Intent intent = new Intent(AdminHomePage.this,AdminHomePage.class);
                startActivity(intent);
                finish();
                Log.e( "onOptionsItemSelected: ", "oldu");
        }
        return super.onOptionsItemSelected(item);
    }
}

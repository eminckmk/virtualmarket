package com.example.veritabaniodevi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class AdminProduct extends AppCompatActivity {

    FloatingActionButton fabProductAdd;
    ArrayList<String> userPriceFromFB;
    ArrayList<String> userCommentFromFB;
    ArrayList<String> userImageFromFB;
    public String a;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference categoryRef ;
    private  AdminProductAdapter adapterCategory;
    FloatingActionButton fabCategoryAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product);


        a = (String) getIntent().getStringExtra("Atıştırmalık");
        categoryRef = db.collection(a);
        userCommentFromFB = new ArrayList<>();
        userPriceFromFB = new ArrayList<>();
        userImageFromFB = new ArrayList<>();


        getDataFromFirestore();
        setUpRecyclerview();

        fabProductAdd = findViewById(R.id.fabProductAdd);
        fabProductAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("onClick: "," "+ a );

                Intent intent = new Intent(AdminProduct.this,AdminProductAdd.class);
                startActivity(intent);
            }
        });
    }

    private void  setUpRecyclerview(){
        Query query = categoryRef.orderBy("comment",Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Categorys> options = new FirestoreRecyclerOptions.Builder<Categorys>().setQuery(query,Categorys.class).build();

        adapterCategory = new AdminProductAdapter(options,userCommentFromFB,userImageFromFB,userPriceFromFB,this);

        RecyclerView recyclerView = findViewById(R.id.rvAdminProduct);
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

        CollectionReference collectionReference = db.collection(a);

        collectionReference.orderBy("comment", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    Toast.makeText(AdminProduct.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                }

                if (queryDocumentSnapshots != null) {

                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {

                        Map<String, Object> data = snapshot.getData();

                        String comment = (String) data.get("comment");
                        String downloadUrl = (String) data.get("downloadurl");
                        String price = (String) data.get("price");

                        userCommentFromFB.add(comment);
                        userImageFromFB.add(downloadUrl);
                        userPriceFromFB.add(price);
                        adapterCategory.notifyDataSetChanged();//

                    }
                }
            }
        });
    }

    public String CategoryNameTransfer(Context mcontext){
        AdminProduct adminProduct = (AdminProduct) mcontext;
        String category = adminProduct.a;
        return category;
    }

    
}
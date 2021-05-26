package com.example.veritabaniodevi;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.squareup.okhttp.internal.DiskLruCache;
import com.squareup.picasso.Picasso;

import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.Locale;
public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.CategoryHolder> {


    private ArrayList<String> userCommentList;
    private ArrayList<String> userImageList;
    private Context context;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    public AdapterCategory(ArrayList<String> userCommentList, ArrayList<String> userImageList) {

        this.userCommentList = userCommentList;
        this.userImageList = userImageList;
    }



    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_category,parent,false);
        return new CategoryHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {

        holder.categoryName.setText(userCommentList.get(position));
        Picasso.get().load(userImageList.get(position)).into(holder.categoryimage);
        holder.categoryimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(),AdminProduct.class));
            }
        });

        holder.imageButtonCategoryDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore = FirebaseFirestore.getInstance();
                firebaseAuth = FirebaseAuth.getInstance();



                String id =  null;
                //String değerini elde edeceğin yer



                DocumentReference documentReference = firebaseFirestore.collection("Category").document("8Upxy7qhD5hlnNUthiDv");
                documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Log.e("s","oldu"+ id);
                        }
                    }
                });

            }
        });

    }



    @Override
    public int getItemCount() { return userCommentList.size();}

    class CategoryHolder extends RecyclerView.ViewHolder {

        ImageView categoryimage;
        TextView categoryName;
        ImageButton imageButtonCategoryDelete;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.textViewCategoryName);
            categoryimage = itemView.findViewById(R.id.imageViewCategoryImage);
            imageButtonCategoryDelete = itemView.findViewById(R.id.imageButtonCategoryDelete);

        }

    }

}




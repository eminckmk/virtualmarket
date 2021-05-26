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
public class AdapterCategory extends FirestoreRecyclerAdapter<Categorys, AdapterCategory.CategoryHolder> {

    private ArrayList<String> userCommentList;
    private ArrayList<String> userImageList;
    private Context context;
    private FirebaseFirestore firebaseFirestore;

    public AdapterCategory(@NonNull FirestoreRecyclerOptions<Categorys> options,ArrayList<String> userCommentList, ArrayList<String> userImageList) {
        super(options);
        this.userCommentList = userCommentList;
        this.userImageList = userImageList;
    }

    @Override
    protected void onBindViewHolder(@NonNull CategoryHolder categoryHolder, int i, @NonNull Categorys categorys) {

        categoryHolder.textCategoryName.setText(userCommentList.get(i));

        Picasso.get().load(userImageList.get(i)).into(categoryHolder.categoryimage);
        categoryHolder.categoryimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(),AdminProduct.class));
            }
        });

        categoryHolder.imageButtonCategoryDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();



                String myId = getSnapshots().getSnapshot(i).getReference().getId();
                Log.e( "onClick: ",myId);
                DocumentReference documentReference = firebaseFirestore.collection("Category").document(myId);



                documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Log.e("s","oldu" );
                        }
                    }
                });


            }
        });
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.card_category,parent,false);
        return new CategoryHolder(v);
    }


    class CategoryHolder extends RecyclerView.ViewHolder{

        ImageView categoryimage;
        TextView textCategoryName;
        ImageButton imageButtonCategoryDelete;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            textCategoryName = itemView.findViewById(R.id.textViewCategoryName);
            imageButtonCategoryDelete = itemView.findViewById(R.id.imageButtonCategoryDelete);
            categoryimage = itemView.findViewById(R.id.imageViewCategoryImage);
        }
    }

}




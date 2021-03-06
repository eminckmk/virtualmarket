package com.example.veritabaniodevi;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdminCategoryAdapter extends FirestoreRecyclerAdapter<Categorys, AdminCategoryAdapter.CategoryHolder> {

    private ArrayList<String> userCommentList;
    private ArrayList<String> userImageList;
    private Context context;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public AdminCategoryAdapter(@NonNull FirestoreRecyclerOptions<Categorys> options, ArrayList<String> userCommentList, ArrayList<String> userImageList, Context context) {
        super(options);
        this.context = context;
        this.userCommentList = userCommentList;
        this.userImageList = userImageList;
    }

    @Override
    protected void onBindViewHolder(@NonNull CategoryHolder categoryHolder, int position, @NonNull Categorys categorys) {

        categoryHolder.textCategoryName.setText(userCommentList.get(position));
        categoryHolder.textPrice.setVisibility(View.GONE);
        Picasso.get().load(userImageList.get(position)).into(categoryHolder.categoryimage);
        categoryHolder.categoryimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,AdminProduct.class);
                intent.putExtra("data",categoryHolder.textCategoryName.getText());
                context.startActivity(intent);
                notifyDataSetChanged();
            }
        });

        categoryHolder.imageButtonCategoryDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String documentId = getSnapshots().getSnapshot(position).getId();
                Log.e( "onBindViewHolder: ",position+" +  "+ documentId );
                DocumentReference documentReference = firebaseFirestore.collection("Category").document(documentId);
                documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            notifyDataSetChanged();
                        }
                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.card_admin,parent,false);
        return new CategoryHolder(v);
    }


    class CategoryHolder extends RecyclerView.ViewHolder{

        ImageView categoryimage;
        TextView textCategoryName;
        ImageButton imageButtonCategoryDelete;
        TextView textPrice;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            textCategoryName = itemView.findViewById(R.id.textViewCategoryName);
            imageButtonCategoryDelete = itemView.findViewById(R.id.imageButtonCategoryDelete);
            categoryimage = itemView.findViewById(R.id.imageViewCategoryImage);
            textPrice = itemView.findViewById(R.id.textPrice);
        }
    }

}




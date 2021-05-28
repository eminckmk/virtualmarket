package com.example.veritabaniodevi;

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
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserCategoryAdapter extends FirestoreRecyclerAdapter<Categorys, UserCategoryAdapter.CategoryHolder> {

    private ArrayList<String> userCommentList;
    private ArrayList<String> userImageList;

    public UserCategoryAdapter(@NonNull FirestoreRecyclerOptions<Categorys> options,ArrayList<String> userCommentList, ArrayList<String> userImageList) {
        super(options);
        this.userCommentList = userCommentList;
        this.userImageList = userImageList;
    }

    @Override
    protected void onBindViewHolder(@NonNull CategoryHolder categoryHolder, int i, @NonNull Categorys categorys) {
        categoryHolder.textCategoryName.setText(userCommentList.get(i));
        Picasso.get().load(userImageList.get(i)).into(categoryHolder.categoryimage);
        categoryHolder.textPrice.setVisibility(View.GONE);
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.usercategory,parent,false);
        return new UserCategoryAdapter.CategoryHolder(v);
    }


    class CategoryHolder extends RecyclerView.ViewHolder{

        ImageView categoryimage;
        TextView textCategoryName;
        TextView textPrice;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            textCategoryName = itemView.findViewById(R.id.textViewCategoryName);
            categoryimage = itemView.findViewById(R.id.imageViewCategoryImage);
            textPrice = itemView.findViewById(R.id.textViewPrice);
        }
    }
}

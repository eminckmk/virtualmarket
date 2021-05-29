package com.example.veritabaniodevi;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserCategoryAdapter extends FirestoreRecyclerAdapter<Categorys, UserCategoryAdapter.CategoryHolder> {

    private ArrayList<String> userCommentList;
    private ArrayList<String> userImageList;
    private Context context;

    public UserCategoryAdapter(@NonNull FirestoreRecyclerOptions<Categorys> options,ArrayList<String> userCommentList, ArrayList<String> userImageList,Context context) {
        super(options);
        this.userCommentList = userCommentList;
        this.userImageList = userImageList;
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull CategoryHolder categoryHolder, int i, @NonNull Categorys categorys) {
        categoryHolder.textCategoryName.setText(userCommentList.get(i));
        Picasso.get().load(userImageList.get(i)).into(categoryHolder.categoryimage);
        categoryHolder.textPrice.setVisibility(View.GONE);

        categoryHolder.imageButtonProductReduce.setVisibility(View.GONE);
        categoryHolder.textProductPiece.setVisibility(View.GONE);
        categoryHolder.imageButtonProductAdd.setVisibility(View.GONE);


        categoryHolder.categoryimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // v.getContext().startActivity(new Intent(v.getContext(),AdminProduct.class));
                Intent intent = new Intent(context,UserProductPage.class);
                intent.putExtra("Atıştırmalık",categoryHolder.textCategoryName.getText());
                context.startActivity(intent);
                notifyDataSetChanged();
            }

        });
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_user,parent,false);
        return new UserCategoryAdapter.CategoryHolder(v);
    }


    class CategoryHolder extends RecyclerView.ViewHolder{

        ImageView categoryimage;
        TextView textCategoryName;
        TextView textPrice;
        TextView textProductPiece;
        ImageButton imageButtonProductReduce;
        ImageButton imageButtonProductAdd;


        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            textCategoryName = itemView.findViewById(R.id.textViewCategoryName);
            categoryimage = itemView.findViewById(R.id.imageViewCategoryImage);
            textPrice = itemView.findViewById(R.id.textViewPrice);
            textProductPiece = itemView.findViewById(R.id.textProductPiece);
            imageButtonProductReduce = itemView.findViewById(R.id.imageButtonProductReduce);
            imageButtonProductAdd = itemView.findViewById(R.id.imageButtonProductAdd);

        }
    }
}

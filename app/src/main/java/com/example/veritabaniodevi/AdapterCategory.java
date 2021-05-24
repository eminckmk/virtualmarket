package com.example.veritabaniodevi;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.CategoryHolder> {

    private ArrayList<String> userCommentList;
    private ArrayList<String> userImageList;
    private Context context;

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

    }

    @Override
    public int getItemCount() { return userCommentList.size();}

    class CategoryHolder extends RecyclerView.ViewHolder {

        ImageView categoryimage;
        TextView categoryName;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.textViewCategoryName);
            categoryimage = itemView.findViewById(R.id.imageViewCategoryImage);

        }
    }

}

   /* private Context mContext;
    private List<Categorys> categorysList;

    public AdapterCategory(Context mContext, List<Categorys> categorysList) {
        this.mContext = mContext;
        this.categorysList = categorysList;
    }

    public class CardViewDesingHolder extends RecyclerView.ViewHolder{
        public ImageView imageViewCategory;
        public TextView textViewCategory;

        public CardViewDesingHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCategory = itemView.findViewById(R.id.imageViewCategoryImage);
            textViewCategory = itemView.findViewById(R.id.textViewCategoryName);
        }
    }

    @NonNull
    @Override
    public CardViewDesingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_category,parent,false);
        return new CardViewDesingHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CardViewDesingHolder holder, int position) {

        Categorys categorys = categorysList.get(position);

        holder.textViewCategory.setText(categorys.getCategoryName());
        holder.imageViewCategory.setImageResource(R.drawable.mailicon);

        holder.imageViewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("onClick:", holder.textViewCategory.getText().toString() );

            }
        });

    }

    @Override
    public int getItemCount() { return categorysList.size(); }

*/




package com.example.veritabaniodevi;

import android.content.Context;
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

public class AdminProductAdapter extends FirestoreRecyclerAdapter<Categorys, AdminProductAdapter.ProductHolder> {

    private ArrayList<String> productNameList;
    private ArrayList<String> productImageList;
    private ArrayList<String> productPriceList;
    public Context context;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public AdminProductAdapter(@NonNull FirestoreRecyclerOptions<Categorys> options, ArrayList<String> productNameList, ArrayList<String> productImageList, ArrayList<String> productPriceList,Context context) {
        super(options);
        this.productNameList = productNameList;
        this.productImageList = productImageList;
        this.productPriceList = productPriceList;
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductHolder productHolder, int i, @NonNull Categorys categorys) {

        productHolder.textProductName.setText(productNameList.get(i));
        productHolder.textProductPrice.setText(productPriceList.get(i));
        Picasso.get().load(productImageList.get(i)).into(productHolder.productimage);
        productHolder.textProductPrice.setVisibility(View.VISIBLE);

        productHolder.imageButtonProductDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String documentId = getSnapshots().getSnapshot(i).getId();
                Log.e( "Document Id: ",""+ documentId );

                AdminProduct adminProduct = new AdminProduct();
                String category= adminProduct.CategoryNameTransfer(context);
                Log.e( "Category Name ", category);

                DocumentReference documentReference = firebaseFirestore.collection(category).document(documentId);

                documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            notifyDataSetChanged();
                            Log.e("Deletion Process","Successful" );
                            Log.e( "Deleted content", String.valueOf(productHolder.textProductName.getText()));
                        }
                    }
                });


            }
        });

    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_category,parent,false);
        return new ProductHolder(v);
    }


    class ProductHolder extends RecyclerView.ViewHolder{

        ImageView productimage;
        TextView textProductName;
        TextView textProductPrice;
        ImageButton imageButtonProductDelete;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);

            textProductName = itemView.findViewById(R.id.textViewCategoryName);
            productimage = itemView.findViewById(R.id.imageViewCategoryImage);
            textProductPrice = itemView.findViewById(R.id.textPrice);
            imageButtonProductDelete = itemView.findViewById(R.id.imageButtonCategoryDelete);


        }
    }
}

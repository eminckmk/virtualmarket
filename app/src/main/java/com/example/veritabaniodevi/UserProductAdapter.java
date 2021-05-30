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
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserProductAdapter extends FirestoreRecyclerAdapter<Categorys, UserProductAdapter.UserProductHolder> {

    private ArrayList<String> productNameList;
    private ArrayList<String> productImageList;
    private ArrayList<String> productPriceList;
    private ArrayList<BasketModel> productFullList ;
    public Context context;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public UserProductAdapter(@NonNull FirestoreRecyclerOptions<Categorys> options, ArrayList<String> productNameList, ArrayList<String> productImageList, ArrayList<String> productPriceList, Context context) {
        super(options);
        this.productNameList = productNameList;
        this.productImageList = productImageList;
        this.productPriceList = productPriceList;
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull UserProductHolder userProductHolder, int i, @NonNull Categorys categorys) {

        userProductHolder.textProductName.setText(productNameList.get(i));
        userProductHolder.textProductPrice.setText(productPriceList.get(i));
        Picasso.get().load(productImageList.get(i)).into(userProductHolder.productimage);
        userProductHolder.textProductPrice.setVisibility(View.VISIBLE);

        if(userProductHolder.textProductPiece.getText().equals("0")){
            userProductHolder.textProductPiece.setVisibility(View.GONE);
            userProductHolder.imageButtonProductReduce.setVisibility(View.GONE);
        }
        else{
            userProductHolder.textProductPiece.setVisibility(View.VISIBLE);
            userProductHolder.imageButtonProductReduce.setVisibility(View.VISIBLE);

            String price = ((String)userProductHolder.textProductPrice.getText());
            String piece =((String)userProductHolder.textProductPiece.getText());
            int totalPiece = Integer.valueOf(price)*Integer.valueOf(piece);

            productFullList = (ArrayList<BasketModel>) PrefConfig.readListFromPref(context,"ListKey13");


            if(productFullList==null){

                productFullList = new ArrayList<>();
                BasketModel b1 = new BasketModel((String) userProductHolder.textProductName.getText(), price, piece,String.valueOf(totalPiece));
                productFullList.add(b1);

                for(int j =0; j<productFullList.size();j++){
                    Log.e("onBindViewHolder: ", String.valueOf("\nÜrün Adı = "+productFullList.get(j).getProductName()) + "\nÜrün Fiyatı = " + productFullList.get(j).getProductPrice()+"TL"+
                            "\nÜrün Adeti = "+productFullList.get(j).getProductPiece()+"Adet"+"\nÜrün Ara Toplam = "+String.valueOf(totalPiece)+"TL");
                }

            }
            else {
                BasketModel b1 = new BasketModel((String) userProductHolder.textProductName.getText(), price, piece,String.valueOf(totalPiece));
                productFullList.add(b1);

                for(int j =0; j<productFullList.size();j++){

                    for(int k =0;k<j;k++){
                        if(productFullList.get(j).getProductName().equals(productFullList.get(k).getProductName())&& Integer.valueOf(productFullList.get(j).getProductPiece())>Integer.valueOf(productFullList.get(k).getProductPiece())){


                            Log.e( "onBindViewHolder: ", "eşittir");
                        }
                        else{
                            Log.e( "onBindViewHolder: ","eşit değil\n+"+"J degeri"+j+ "\nK degeri"+k+"\nj Degeri adet "+ String.valueOf(Integer.valueOf(productFullList.get(j).getProductPiece())+"\nK Degeri adet "+ String.valueOf(Integer.valueOf(productFullList.get(k).getProductPiece()))));}
                    }


                    Log.e("onBindViewHolder: ", String.valueOf("\nÜrün Adı = "+productFullList.get(j).getProductName()) + "\nÜrün Fiyatı = " + productFullList.get(j).getProductPrice()+"TL"+
                            "\nÜrün Adeti = "+productFullList.get(j).getProductPiece()+"Adet"+"\nÜrün Ara Toplam = "+String.valueOf(totalPiece)+"TL");
                }

            }

            PrefConfig.writeListInPref(context,productFullList,"ListKey13");


            //Log.e( "onBindViewHolder: ", String.valueOf(productFullList));
        }

        userProductHolder.imageButtonProductAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userProductHolder.textProductPiece.setText(String.valueOf(Integer.valueOf((String) userProductHolder.textProductPiece.getText())+1));
                notifyDataSetChanged();
            }
        });

        userProductHolder.imageButtonProductReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userProductHolder.textProductPiece.setText(String.valueOf(Integer.valueOf((String) userProductHolder.textProductPiece.getText())-1));
                notifyDataSetChanged();
            }
        });

    }

    @NonNull
    @Override
    public UserProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_user,parent,false);
        return new UserProductHolder(v);
    }

    class UserProductHolder extends RecyclerView.ViewHolder{

        ImageView productimage;
        TextView textProductName;
        TextView textProductPrice;
        TextView textProductPiece;
        ImageButton imageButtonProductReduce;
        ImageButton imageButtonProductAdd;

        public UserProductHolder(@NonNull View itemView) {
            super(itemView);

            textProductName = itemView.findViewById(R.id.textViewCategoryName);
            productimage = itemView.findViewById(R.id.imageViewCategoryImage);
            textProductPrice = itemView.findViewById(R.id.textViewPrice);
            textProductPiece = itemView.findViewById(R.id.textProductPiece);
            imageButtonProductReduce = itemView.findViewById(R.id.imageButtonProductReduce);
            imageButtonProductAdd = itemView.findViewById(R.id.imageButtonProductAdd);


        }
    }


}

package com.example.veritabaniodevi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.MemoryFile;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.google.common.base.Converter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.installations.remote.TokenResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class HomePage extends AppCompatActivity {

    private RecyclerView rv;
    private ArrayList<Categorys> categorysArrayList;
    private AdapterCategory adapter;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<String> userEmailFromFB;
    ArrayList<String> userCommentFromFB;
    ArrayList<String> userImageFromFB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);



       /* rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));


        Categorys c1 = new Categorys("Tüm ürünler","tumurunler");
        Categorys c2 = new Categorys("Su","su");
        Categorys c3 = new Categorys("Meyve & Sebze","meyve");
        Categorys c4 = new Categorys("Temel Gıda","temel");
        Categorys c5 = new Categorys("Atıştırmalık","atistirmalik");
        Categorys c6 = new Categorys("Dondurma","Dondurma");
        Categorys c7 = new Categorys("İçecek","icecek");
        Categorys c8 = new Categorys("Süt & Kahvaltı","kahvalti");
        Categorys c9 = new Categorys("Kişisel Bakım","bakim");
        Categorys c10 = new Categorys("Giyim","giyim");
        Categorys c11 = new Categorys("Teknoloji","teknology");

        categorysArrayList = new ArrayList<>();
        categorysArrayList.add(c1);
        categorysArrayList.add(c2);
        categorysArrayList.add(c3);
        categorysArrayList.add(c4);
        categorysArrayList.add(c5);
        categorysArrayList.add(c6);
        categorysArrayList.add(c7);
        categorysArrayList.add(c8);
        categorysArrayList.add(c9);
        categorysArrayList.add(c10);
        categorysArrayList.add(c11);

        adapter = new AdapterCategory(this,categorysArrayList);
        rv.setAdapter(adapter);*/


    }




}
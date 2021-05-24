package com.example.veritabaniodevi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AdminProduct extends AppCompatActivity {

    FloatingActionButton fabProductAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product);

        fabProductAdd = findViewById(R.id.fabProductAdd);

        fabProductAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminProduct.this,AdminProductAdd.class);
                startActivity(intent);
            }
        });
    }
}
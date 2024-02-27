package com.example.sosapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Product_Revolar_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_revolar_page);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pink)));
        Button Payment_btn = findViewById(R.id.Payment_btn);
        Payment_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Product_Revolar_Page.this, Payment_Gateway_Page.class);
                intent.putExtra("Product_name", "Revolar");
                intent.putExtra("Product_price" , "6543");
                startActivity(intent);
            }
        });
    }
}
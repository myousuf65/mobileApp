package com.example.two_tables;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class cart extends AppCompatActivity implements RecyclerViewInterface {

    private RecyclerView recyclerView;

    private List<cartModel> allRetreivedMovies;

    List<String> price_with_sign;
    private TextView priceText;
    private Button chckout;

    private String MOVIEname;

    private Button back;
    private cartAdapter adapter;
    private int sum = 0;


    @Override
    public void onItemclick(int position) {
        //nothing happens
    }

    @Override
    public void onItemLongClick(int position) {
        //call delete method

        databaseHelper helper = new databaseHelper(cart.this);

        String name = allRetreivedMovies.get(position).name;


        helper.deletecartItem(name);


        allRetreivedMovies.remove(position);
        adapter.notifyItemRemoved(position);

        price_with_sign = helper.getprice();

        sum = 0;


        for(int i=0; i<price_with_sign.size();i++){

            sum += Double.valueOf(price_with_sign.get(i).substring(1));
        }

        df.setRoundingMode(RoundingMode.DOWN);

        priceText.setText(df.format(sum));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.cartrecyclerview);
        priceText = findViewById(R.id.price);
        chckout = findViewById(R.id.button);
        back = findViewById(R.id.button_back);


        getSupportActionBar().hide();

        MOVIEname = getIntent().getStringExtra("name");
        String price = getIntent().getStringExtra("price");
        byte[] poster = getIntent().getByteArrayExtra("poster");


        databaseHelper helper = new databaseHelper(cart.this);

        helper.addCart(MOVIEname, price, poster);

        allRetreivedMovies = helper.getAllCarts();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cart.this, MainActivity.class);
                startActivity(intent);
            }
        });
        adapter = new cartAdapter(this, allRetreivedMovies, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

//
        price_with_sign = helper.getprice();
//
        for(int i=0; i<price_with_sign.size();i++){
            sum += Double.valueOf(price_with_sign.get(i).substring(1));
        }

        df.setRoundingMode(RoundingMode.DOWN);

        priceText.setText(df.format(sum));


        chckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cart.this, checkout.class);
                intent.putExtra("total_price",df.format(sum) );
                startActivity(intent);
            }
        });
    }

    private static final DecimalFormat df = new DecimalFormat("0.00");


}
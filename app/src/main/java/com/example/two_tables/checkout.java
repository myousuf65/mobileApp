package com.example.two_tables;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class checkout extends AppCompatActivity {

    private Button back;
    private ListView listView;
    private TextView pricing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        back = findViewById(R.id.button2);
        listView = findViewById(R.id.listview);
        pricing = findViewById(R.id.pricing);


        String totalprice = getIntent().getStringExtra("total_price");

        databaseHelper helper = new databaseHelper(checkout.this);

        List<String> allname =  helper.get_name();

        Adapter adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,allname);
        listView.setAdapter((ListAdapter) adapter);

        helper.deletecartTable();


        pricing.setText("Total Price : " + totalprice);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(checkout.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
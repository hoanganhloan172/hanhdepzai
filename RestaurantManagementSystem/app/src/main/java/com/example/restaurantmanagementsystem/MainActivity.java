package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.User;

public class MainActivity extends AppCompatActivity {

    private TextView contact, order, mycustomer,orderhistory, userprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mycustomer = findViewById(R.id.mycustomer);
        contact = findViewById(R.id.contact);
        order = findViewById(R.id.tvorder1);
        orderhistory = findViewById(R.id.historyOrder);
        userprofile = findViewById(R.id.userprofile);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, OrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("order", "order" );
                intent1.putExtras(bundle);

                startActivityForResult(intent1,10);
            }
        });
        orderhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, OrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("order", "orderhistory" );
                intent1.putExtras(bundle);
                startActivityForResult(intent1,10);
            }
        });
        mycustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, MyCustomerActivity.class);
                startActivity(intent1);
            }
        });

        userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = ContextDatabase.getInstance(MainActivity.this).userDao().getUserById(1);

                Intent intent1 = new Intent(MainActivity.this, UserProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                intent1.putExtras(bundle);
                startActivityForResult(intent1,10);
            }
        });
    }
}
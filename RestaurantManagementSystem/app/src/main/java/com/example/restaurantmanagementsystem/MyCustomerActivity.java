package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.example.restaurantmanagementsystem.adapter.MyCustomerAdapter;
import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.Order;
import com.example.restaurantmanagementsystem.entity.User;

import java.util.ArrayList;
import java.util.List;

public class MyCustomerActivity extends AppCompatActivity {
    private RecyclerView rcvMyCustomer;
    List<User> listUser = new ArrayList<>();
    List<User> ListAllUser = new ArrayList<>();
    List<Order> ListAllOrder = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_customer);
        rcvMyCustomer = findViewById(R.id.rcv_mycustomer);

//        getListUser();

        MyCustomerAdapter myCustomerAdapter = new MyCustomerAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvMyCustomer.setLayoutManager(linearLayoutManager);



        listUser = ContextDatabase.getInstance(this).userDao().getListUserOrdered();


        myCustomerAdapter.setData(listUser);
        rcvMyCustomer.setAdapter(myCustomerAdapter);

    }



}
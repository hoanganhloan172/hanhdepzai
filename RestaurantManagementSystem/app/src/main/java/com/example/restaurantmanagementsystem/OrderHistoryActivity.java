package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.adapter.OrderAdapter;
import com.example.restaurantmanagementsystem.adapter.OrderHistoryAdapter;
import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity {
    private  static final int MY_REQUEST_CODE  = 10;
    private RecyclerView rcvOrder;
    private TextView tvordermanager;
    private EditText editsearchh;
    private ImageView iconsearchh;


    private OrderHistoryAdapter orderHistoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        editsearchh = findViewById(R.id.editsearchorderhistory);
        iconsearchh = findViewById(R.id.iconsearchorderhistory);
        tvordermanager = findViewById(R.id.tvordermanagerorderhistory);
        iconsearchh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });
//        getListOrder();



        orderHistoryAdapter =new OrderHistoryAdapter(new OrderHistoryAdapter.clickItemOrder() {


            @Override
            public void vieworder(Order order) {
                clickvieworder(order,"orderhistory");

            }
        });

        rcvOrder = findViewById(R.id.rcv_orderorderhistory);
//        OrderAdapter orderAdapter = new OrderAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        rcvOrder.setLayoutManager(linearLayoutManager);

        orderHistoryAdapter.setData(ContextDatabase.getInstance(this).orderDao().getAllbyStatusHistory());

        rcvOrder.setAdapter(orderHistoryAdapter);

    }

    private void search() {
        String keyword = editsearchh.getText().toString().trim();

            orderHistoryAdapter.setData(ContextDatabase.getInstance(this).orderDao().getOrderHistoryBysearch(keyword));


    }





    private void clickvieworder(Order order, String check) {
        Intent intent = new Intent(OrderHistoryActivity.this, ViewOrderDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_order", order);
        bundle.putSerializable("checkcheck",check);
        intent.putExtras(bundle);
        startActivityForResult(intent, MY_REQUEST_CODE);
    }

    private List<Order> getListOrder() {
        List<Order> list = new ArrayList<>();



        list.add(new Order("11/10/2022", 123, 1 , "inprogessing"));

        list.add(new Order("11/10/2021", 11, 2 , "inprogessing"));

        list.add(new Order("11/10/2019", 156, 3 , "inprogessing"));

        list.add(new Order("11/10/2020", 13312, 4 , "inprogessing"));

        list.add(new Order("11/10/2018", 12123, 1 , "inprogessing"));
        for (int i = 0; i < list.size(); i++) {
            ContextDatabase.getInstance(this).orderDao().insertAll(list.get(i));
        }
        return list;
    }

//    private List<Order> getListOrderbyStatus(List<Order> list){
//         List<Order> list1 = new ArrayList<>();
//
//
//        for (int i = 0; i < list.size(); i++) {
//            if(list.get(i).getStatus().equals("inprogessing")){
//
//            list1.add(list.get(i));
//            }
//        }
//
//        return list1;
//    }
}
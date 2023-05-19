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
import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private  static final int MY_REQUEST_CODE  = 10;
    private RecyclerView rcvOrder;
    private TextView tvordermanager;
    private EditText editsearchh;
    private ImageView iconsearchh;


    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        editsearchh = findViewById(R.id.editsearch);
        iconsearchh = findViewById(R.id.iconsearch);
        tvordermanager = findViewById(R.id.tvordermanager);
        iconsearchh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });
//        getListOrder();



        orderAdapter =new OrderAdapter(new OrderAdapter.clickItemOrder() {
            @Override
            public void acceptorder(Order order) {

                clickacceptorder(order);
            }

            @Override
            public void declineorder(Order order) {
                clickdeclineorder(order);

            }

            @Override
            public void vieworder(Order order) {
                clickvieworder(order,"order");

            }
        });

        rcvOrder = findViewById(R.id.rcv_order);
//        OrderAdapter orderAdapter = new OrderAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        rcvOrder.setLayoutManager(linearLayoutManager);

            orderAdapter.setData(ContextDatabase.getInstance(this).orderDao().getallbyStatus());

        rcvOrder.setAdapter(orderAdapter);

    }

    private void search() {
        String keyword = editsearchh.getText().toString().trim();
        orderAdapter.setData(ContextDatabase.getInstance(this).orderDao().getOrderBysearch(keyword));
    }

    private void clickdeclineorder(Order order) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Decline this order")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        order.setStatus("delete");
                        ContextDatabase.getInstance(OrderActivity.this).orderDao().updateStatus(order);

                        Toast.makeText(OrderActivity.this,  "Decline order successfully",Toast.LENGTH_SHORT).show();
                        orderAdapter.setData(ContextDatabase.getInstance(OrderActivity.this).orderDao().getallbyStatus());
                    }
                })
                .setNegativeButton("No",null)
                .show();

    }

    private void clickacceptorder(Order order) {


//        Toast.makeText(this,  "Done order",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(OrderActivity.this, ArrangeTableActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("orderarrange", order);
        intent.putExtras(bundle);
        startActivityForResult(intent,10);
    }

    private void clickvieworder(Order order, String check) {
        Intent intent = new Intent(OrderActivity.this, ViewOrderDetailActivity.class);
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
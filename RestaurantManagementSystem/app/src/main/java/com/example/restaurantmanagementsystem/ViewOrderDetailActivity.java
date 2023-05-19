package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.restaurantmanagementsystem.adapter.OrderDetailAdapter;
import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.Order;
import com.example.restaurantmanagementsystem.entity.OrderDetail;
import com.example.restaurantmanagementsystem.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ViewOrderDetailActivity extends AppCompatActivity {

    private TextView tvodcustomername, tvodstatus, tvoddate, tvodphonenumber;
    private TextView tvodemail, tvodtotal;

    private RecyclerView rcvorderdetail;
    private ImageView backViewOrderDetail;
    private Order order;
    private double totalPrice = 0;
    List<OrderDetail> listOrderDetailById = new ArrayList<>();
    List<OrderDetail> listOrderItemDetail = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_detail);
        initUI();

        String check = String.valueOf(getIntent().getExtras().get("checkcheck"));
        order = (Order) getIntent().getExtras().get("object_order");
        listOrderItemDetail = ContextDatabase.getInstance(this).orderDetailDao().getlistOrderItemDetail(order.getOrderId());


        User user = ContextDatabase.getInstance(this).userDao().getUserById(order.getCustomerId());

        if (order != null) {
            tvodcustomername.setText(user.getFullName());
            tvodstatus.setText(order.getStatus());
            tvoddate.setText(order.getOrderDate());
            tvodphonenumber.setText(user.getMobile());
            tvodemail.setText(user.getEmail());

            OrderDetailAdapter orderDetailAdapter = new OrderDetailAdapter(this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            rcvorderdetail.setLayoutManager(linearLayoutManager);


//            listOrderDetailById = getListItemById(getListOrderDetailbyId(order.getOrderId()));


            orderDetailAdapter.setData(listOrderItemDetail);
            rcvorderdetail.setAdapter(orderDetailAdapter);


            // total
            totalPrice = CalTotal(listOrderItemDetail);
            tvodtotal.setText(totalPrice + "");
              if(check.equals("order")){
                  backViewOrderDetail.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          Intent intentback = new Intent(ViewOrderDetailActivity.this, OrderActivity.class);
                          Bundle bundle = new Bundle();
                          bundle.putSerializable("order", check);

                          intentback.putExtras(bundle);
                          startActivityForResult(intentback, 10);
                      }
                  });
              }else{
                  backViewOrderDetail.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          Intent intentback = new Intent(ViewOrderDetailActivity.this, OrderHistoryActivity.class);
                          Bundle bundle = new Bundle();
                          bundle.putSerializable("order", check);
                          intentback.putExtras(bundle);
                          startActivityForResult(intentback, 10);
                      }
                  });
              }

        }
    }

    private double CalTotal(List<OrderDetail> list) {
        double total = 0;

        for (int i = 0; i < list.size(); i++) {
            total = total + (list.get(i).getQuantity() * list.get(i).getItemPrice());
        }
        return total;
    }





    private void initUI() {
        backViewOrderDetail = findViewById(R.id.back_vieworderdetail);
        tvodcustomername = findViewById(R.id.tvod_customerName);
        tvodstatus = findViewById(R.id.tvod_status);

        tvoddate = findViewById(R.id.tvod_date);
        tvodphonenumber = findViewById(R.id.tvod_phonenumber);
        tvodemail = findViewById(R.id.tvod_email);
        tvodtotal = findViewById(R.id.tvod_total);
        rcvorderdetail = findViewById(R.id.rcv_orderdetail);


    }


    private List<OrderDetail> getListOrderDetail() {
        List<OrderDetail> listorderdetail = new ArrayList<>();

        listorderdetail.add(new OrderDetail(1, 2, 10, 14));
        listorderdetail.add(new OrderDetail(1, 3, 11, 111));
        listorderdetail.add(new OrderDetail(1, 4, 11, 1000));
        listorderdetail.add(new OrderDetail(2, 1, 20, 99));
        listorderdetail.add(new OrderDetail(2, 2, 4, 14));
        listorderdetail.add(new OrderDetail(2, 3, 6, 111));
        listorderdetail.add(new OrderDetail(3, 3, 10, 111));
        listorderdetail.add(new OrderDetail(3, 4, 14, 1000));
        listorderdetail.add(new OrderDetail(3, 5, 15, 44));
        listorderdetail.add(new OrderDetail(3, 6, 20, 33));

        for (int i = 0; i < listorderdetail.size(); i++) {
            ContextDatabase.getInstance(this).orderDetailDao().insertOrder(listorderdetail.get(i));
        }

        return listorderdetail;
    }


}
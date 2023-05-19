package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.Cart;
import com.example.restaurantmanagementsystem.entity.Order;
import com.example.restaurantmanagementsystem.entity.OrderDetail;
import com.example.restaurantmanagementsystem.entity.User;
import com.example.restaurantmanagementsystem.respository.CartRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class CheckOutActivity extends AppCompatActivity {

    private TextView checkoutTotalPrice,checkoutName,checkoutPhone,checkoutDate;
    private Button btnCheckoutSave;
    private User user;
    private CartRepository cartRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("User_Id",0);

        user = ContextDatabase.getInstance(this).userDao().getUserById(userId);

        checkoutTotalPrice = findViewById(R.id.checkout_total_price);
        checkoutName = findViewById(R.id.checkout_name);
        checkoutPhone = findViewById(R.id.checkout_phone);
        checkoutDate = findViewById(R.id.checkout_date);

        double ttPrice = (Double) getIntent().getExtras().get("total_price");

        checkoutTotalPrice.setText(String.valueOf(ttPrice));

        checkoutName.setText(user.getUserName());
        checkoutPhone.setText(user.getMobile());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        String date = sdf.format(c.getTime());
        checkoutDate.setText(date);

        cartRepository = new CartRepository(this);

        btnCheckoutSave = findViewById(R.id.btn_checkout_save);
        btnCheckoutSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Order order = new Order(date,ttPrice,userId,"inprogessing",user.getUserName());
                ContextDatabase.getInstance(CheckOutActivity.this).orderDao().insertAll(order);

                Order orderLast = ContextDatabase.getInstance(CheckOutActivity.this).orderDao().getLastOrder();

                List<Cart> carts = cartRepository.getCartByUser(userId);

                for(int i = 0; i <carts.size();i++){
                    OrderDetail orderDetail = new OrderDetail(orderLast.getOrderId(),
                            carts.get(i).getItemId(),carts.get(i).getQuantity(),
                            carts.get(i).getPrice(),carts.get(i).getItemName());
                    ContextDatabase.getInstance(CheckOutActivity.this).orderDetailDao().insertOrder(orderDetail);
                }

                cartRepository.deleteCartByUser(userId);

                Intent intent = new Intent(CheckOutActivity.this,SuccsessActivity.class);
                startActivity(intent);
            }
        });

        ImageButton btnBack = findViewById(R.id.back_on_checkout);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckOutActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });
    }



}
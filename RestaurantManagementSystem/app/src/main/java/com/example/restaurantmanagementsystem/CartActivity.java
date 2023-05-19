package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.adapter.CartAdapter;
import com.example.restaurantmanagementsystem.entity.Cart;
import com.example.restaurantmanagementsystem.respository.CartRepository;
import com.example.restaurantmanagementsystem.respository.ItemRepository;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private List<Cart> cartList = new ArrayList<>();

    private static final int CART_REQUEST = 27;

    RecyclerView recyclerView;
    private ItemRepository itemRepository = null;
    private CartRepository cartRepository = null;
    private CartAdapter cartAdapter;
    private TextView totalPrice;
    private double ttPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartAdapter = new CartAdapter(new CartAdapter.IClickCart() {
            @Override
            public void plus(Cart cart) {
                handlePlus(cart);
            }

            @Override
            public void minus(Cart cart) {
                handleMinus(cart);
            }

            @Override
            public void delete(Cart cart) {
                handleDelete(cart);
            }
        });
        recyclerView = findViewById(R.id.recycle_view_cart);
//        cartAdapter.setData(new ArrayList<>());

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadData();
        recyclerView.setAdapter(cartAdapter);

        totalPrice = findViewById(R.id.total_price);
        totalPrice.setText(Double.toString(ttPrice));

        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("User_Id",0);
        List<Cart> carts = cartRepository.getCartByUser(userId);

        Button checkout = findViewById(R.id.btn_checkout);
        if(carts==null || carts.size()==0){
            checkout.setEnabled(false);
        }

        ImageButton btnBack = findViewById(R.id.back_on_cart);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this,UserHomeActivity.class);
                startActivity(intent);
            }
        });

//        Button checkout = findViewById(R.id.btn_checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(CartActivity.this,CheckOutActivity.class);
//                startActivity(intent);
                Intent intent = new Intent(CartActivity.this,CheckOutActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("total_price",ttPrice);
                intent.putExtras(bundle);
                startActivityForResult(intent,CART_REQUEST);
            }
        });
    }

    private void loadData(){
        ttPrice =0;
        cartRepository = new CartRepository(this);

        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("User_Id",0);
        List<Cart> carts = cartRepository.getCartByUser(userId);
        cartAdapter.setData(carts);
        for(int i = 0; i <carts.size();i++){
            ttPrice += carts.get(i).getPrice()*carts.get(i).getQuantity();
        }
    }


    private void handlePlus(Cart cart){
        cartRepository = new CartRepository(this);
        cartRepository.plusQuantity(cart.getItemId());
        loadData();
        totalPrice.setText(Double.toString(ttPrice));
    }
    private void handleMinus(Cart cart){
        cartRepository = new CartRepository(this);
        if(cart.getQuantity()==1) {
            new AlertDialog.Builder(this)
                    .setTitle("Confirm Decline this Food")
                    .setMessage("Are you sure?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            cartRepository.delete(cart);
                            loadData();
                            totalPrice.setText(Double.toString(ttPrice));
                            Toast.makeText(CartActivity.this, "Delete successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No",null)
                    .show();
        }else {
            cartRepository.minusQuantity(cart.getItemId());
            loadData();
            totalPrice.setText(Double.toString(ttPrice));
        }
    }
    private void handleDelete(Cart cart){
        cartRepository = new CartRepository(this);
        new AlertDialog.Builder(this)
                .setTitle("Confirm Decline this order")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cartRepository.delete(cart);
                        loadData();
                        totalPrice.setText(Double.toString(ttPrice));
                        Toast.makeText(CartActivity.this, "Delete successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }
}
package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.entity.Cart;
import com.example.restaurantmanagementsystem.entity.Item;
import com.example.restaurantmanagementsystem.respository.CartRepository;

public class FoodDetailActivity extends AppCompatActivity {
    private TextView detailItemName,detailItemCate,detailItemPrice,detailItemDescription;
    private Button addCart;

    private CartRepository cartRepository = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        cartRepository = new CartRepository(this);

        detailItemName = findViewById(R.id.detail_item_name);
//        detailItemCate = findViewById(R.id.detail_item_cate);
        detailItemPrice = findViewById(R.id.detail_item_price);
        detailItemDescription = findViewById(R.id.detail_item_detail);

        Item item = (Item) getIntent().getExtras().get("object_item_user");

        if(item!=null){
            detailItemName.setText(item.getItemName());
            detailItemPrice.setText(String.valueOf(item.getPrice()));
//            detailItemCate.setText(item.getCategoryName());
            detailItemDescription.setText(item.getDescription());
        }

        addCart = findViewById(R.id.btn_detail_item_add_cart);
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cart check = cartRepository.getCartByitemId(item.getItemId());

                SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
                int userId = sharedPreferences.getInt("User_Id",0);

                if(check==null) {
                    Cart c = new Cart(userId,item.getItemId(), item.getItemName(), "",
                            item.getPrice(), 1, item.isStatus(), item.getCategoryId(),
                            item.getCategoryName(), item.getDescription());
                    cartRepository.insertCart(c);
                    Toast.makeText(FoodDetailActivity.this, "Add to cart successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(FoodDetailActivity.this, "You have this item in your cart", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ImageButton btnBack = findViewById(R.id.back_on_ite_detail);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodDetailActivity.this,UserHomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
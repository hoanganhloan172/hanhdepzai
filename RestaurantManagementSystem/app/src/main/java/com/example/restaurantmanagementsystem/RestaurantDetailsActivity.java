package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.Restaurant;

public class RestaurantDetailsActivity extends AppCompatActivity {
    private EditText edtRestaurantname,editdomain, edtPhone, edtStreetname, edtAreaName, editCityName, editStateName, editCountryName;
    private Button btnSaveChange;
    private Restaurant restaurant1;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        initUI();

        restaurant1 = ContextDatabase.getInstance(this).restaurantDao().restaurantInformation();
        if (restaurant1 == null) {
            Restaurant restaurant = new Restaurant("XOXO Restaurant", ".com", "0985910749", "QL21", "Khu CN Cao", "Ha Noi", "Hoa Lac", "Viet Nam");
            ContextDatabase.getInstance(this).restaurantDao().addRestaurant(restaurant);
            restaurant1 = ContextDatabase.getInstance(this).restaurantDao().restaurantInformation();

        }

        System.out.println(restaurant1);

        edtRestaurantname.setText(restaurant1.getRestaurantName());
        editdomain.setText(restaurant1.getDomainName());
        edtPhone.setText(restaurant1.getPhone());
        edtStreetname.setText(restaurant1.getStreetName());
        edtAreaName.setText(restaurant1.getArea());
        editCityName.setText(restaurant1.getCity());
        editStateName.setText(restaurant1.getState());
        editCountryName.setText(restaurant1.getCountry());

        btnSaveChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeResInformation(restaurant1);
            }
        });

//        imgBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(RestaurantDetailsActivity.this, HomeAdminActivity.class);
//                startActivity(intent);
//            }
//        });


    }


    private void changeResInformation(Restaurant restaurant) {
        String restaurantname = edtRestaurantname.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String streetname = edtStreetname.getText().toString().trim();
        String areaName = edtAreaName.getText().toString().trim();
        String cityName = editCityName.getText().toString().trim();

        String stateName = editStateName.getText().toString().trim();

        String countryName = editCountryName.getText().toString().trim();

        if (TextUtils.isEmpty(restaurantname) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(streetname) || TextUtils.isEmpty(areaName) || TextUtils.isEmpty(stateName) || TextUtils.isEmpty(countryName) || TextUtils.isEmpty(cityName)) {
            return;
        }

        restaurant.setRestaurantName(restaurantname);
        restaurant.setPhone(phone);
        restaurant.setStreetName(streetname);
        restaurant.setArea(areaName);
        restaurant.setCity(cityName);
        restaurant.setState(stateName);
        restaurant.setCountry(countryName);

        ContextDatabase.getInstance(this).restaurantDao().saveChangeInformation(restaurant);
        Toast.makeText(this, "Update Restaurant Information Successfully", Toast.LENGTH_SHORT).show();

        edtRestaurantname.setText(restaurantname);
        edtPhone.setText(phone);
        edtStreetname.setText(streetname);
        edtAreaName.setText(areaName);
        editCityName.setText(cityName);
        editCountryName.setText(countryName);
        editStateName.setText(stateName);


    }

    void initUI() {

        edtRestaurantname = findViewById(R.id.edit_restaurant_name);
        edtPhone = findViewById(R.id.edit_phone);
        edtStreetname = findViewById(R.id.edit_street_name);
        edtAreaName = findViewById(R.id.edit_area_name);
        editCityName = findViewById(R.id.edit_city_name);
        editdomain = findViewById(R.id.edit_domain_name);
        editStateName = findViewById(R.id.edit_state_name);
        editCountryName = findViewById(R.id.edit_country_name);

        btnSaveChange = findViewById(R.id.btn_save_restaurant_details);
//        imgBack = findViewById(R.id.imv_backrestraurant);
    }

}
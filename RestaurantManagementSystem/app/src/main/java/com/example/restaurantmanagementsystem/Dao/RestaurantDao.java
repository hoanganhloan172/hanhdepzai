package com.example.restaurantmanagementsystem.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.restaurantmanagementsystem.entity.Restaurant;

@Dao
public interface RestaurantDao {
    @Insert
    void addRestaurant(Restaurant restaurant);

    @Query("SELECT * FROM restaurant where restaurant_id = 1")
    Restaurant restaurantInformation();




    @Update
    void saveChangeInformation(Restaurant restaurant);
}

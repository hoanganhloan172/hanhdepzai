package com.example.restaurantmanagementsystem.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.restaurantmanagementsystem.entity.Meal;

import java.util.List;

@Dao
public interface MealDao {
    @Insert
    void insertMeal(Meal meal);



    @Query("Select * from meal where status like 'waiting'")
    List<Meal> getListMealStatus();

    @Update
    void updateMeal(Meal meal);
}

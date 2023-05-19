package com.example.restaurantmanagementsystem.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.restaurantmanagementsystem.entity.Category;

import java.util.List;

@Dao
public interface CategoryDAO {
    @Query("select * from category")
    List<Category> getAllCategory();

    @Query("select * from category where category_status = 1")
    List<Category> getAllCategoryByStatus();

    @Query("Select * from category c where c.category_name like :category")
    List<Category> select(String category);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Category category);

    @Query("Select * from category c where c.category_name like '%' || :name || '%'")
    List<Category> searchCategory(String name);

    @Update
    void update(Category ... categories);
}

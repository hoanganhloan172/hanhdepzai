package com.example.restaurantmanagementsystem.Dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.restaurantmanagementsystem.entity.Item;

import java.util.List;

@Dao
public interface ItemDAO {
    @Query("select * from item i join category c on i.category_id = c.categoryId where c.category_status=1")
    List<Item> getAllWords();

    @Query("select * from item i join category c on i.category_id = c.categoryId where c.category_status=1 and i.status=1")
    List<Item> getItemForMenu();

    @Query("Select * from Item i where i.item_name like :item")
    List<Item> select(String item);

    @Query("Select * from Item i join category c on i.category_id = c.categoryId " +
            "where i.item_name like '%' || :name || '%' And c.category_status=1")
    List<Item> searchItem(String name);

    @Query("Select count(*) from Item i where i.status=1")
    int countItemActive();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Item word);

    @Update
    void update(Item word);

}

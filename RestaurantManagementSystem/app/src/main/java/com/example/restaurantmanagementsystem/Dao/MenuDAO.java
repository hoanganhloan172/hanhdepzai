package com.example.restaurantmanagementsystem.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.restaurantmanagementsystem.entity.Menu;

import java.util.List;

@Dao
public interface MenuDAO {
    @Query("select * from menu")
    List<Menu> getAllMenu();

    @Query("Select * from menu m where m.menu_name like :menu")
    List<Menu> select(String menu);

    @Query("Select * from menu m where m.menu_name like '%' || :name || '%'")
    List<Menu> searchMenu(String name);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Menu menu);

    @Update
    void update(Menu ... menus);
}

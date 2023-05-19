package com.example.restaurantmanagementsystem.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.restaurantmanagementsystem.entity.MenuItem;

import java.util.List;

@Dao
public interface MenuItemDAO {
    @Query("select * from menu_item")
    List<MenuItem> getAllMenuItem();

    @Query("Select * from menu_item m where m.menu_id = :menuId")
    List<MenuItem> selectItemByMenu(int menuId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MenuItem menuItem);

    @Update
    void update(MenuItem ... menuItems);

    @Delete
    void delete(MenuItem menuItem);

    @Query("Delete from menu_item  where menu_id = :menuId  AND item_id = :itemId")
    void deletemenuItemBy2Id(int menuId,int itemId);

    @Query("Delete from menu_item where item_id = :itemId")
    void deleteMenuItemByItemId(int itemId);
}

package com.example.restaurantmanagementsystem.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.restaurantmanagementsystem.entity.Table;

import java.util.List;
@Dao
public interface TableDao {

    @Insert
    void insertTable(Table table);

    @Query("SELECT * FROM `table`")
    List<Table> getListTable();

    @Update
    void updateTable(Table table);

    @Delete
    void deleteTable(Table table);

    @Query("SELECT * FROM `table` WHERE id= :id")
    Table checkTable(int id);

    @Query("SELECT * FROM `table` WHERE status like 'inactive'")
    List<Table> getListTableStatus();
}

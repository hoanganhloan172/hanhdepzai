package com.example.restaurantmanagementsystem.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.restaurantmanagementsystem.entity.Contact;

import java.util.List;

@Dao
public interface ContactDao {


    @Insert
    void insertContact(Contact contact);

    @Query("Select * from Contact")
    List<Contact> getAll();
}

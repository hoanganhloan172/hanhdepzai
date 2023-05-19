package com.example.restaurantmanagementsystem.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.restaurantmanagementsystem.entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);

    @Query("Select * from User")
    List<User> getAll();

    @Query("Select * from User where user_id = :id")
    User getUserById(int id);

    @Query("Select distinct u.* from user u join [order] o on u.user_id = o.customer_id")
    List<User> getListUserOrdered();


    @Update
    void UpdateProfile(User user);

    @Insert
    void registerUser(User user);

    @Query("SELECT * FROM user WHERE email = :em ")
    User checkUser(String em);


    @Update
    void updatePassword(User user);

    @Query("SELECT * FROM user WHERE role_name like 'user' ")
    List<User> getListUserRole( );
}

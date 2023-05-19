package com.example.restaurantmanagementsystem.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.restaurantmanagementsystem.entity.OrderDetail;

import java.util.List;

@Dao
public interface OrderDetailDao {

    @Insert
    void insertOrder(OrderDetail orderDetail);


    @Query("Select od.order_detail_id,od.order_id,od.item_id,od.quantity,od.item_price,i.item_name from orderdetail od join item i on od.item_id = i.itemId where order_id = :orderid")
    List<OrderDetail> getlistOrderItemDetail(int orderid);

    @Query("Select * from orderdetail ")
    List<OrderDetail> getlistAll();
}

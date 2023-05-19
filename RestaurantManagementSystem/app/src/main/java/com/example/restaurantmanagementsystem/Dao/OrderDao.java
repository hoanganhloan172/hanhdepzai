package com.example.restaurantmanagementsystem.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.restaurantmanagementsystem.entity.Order;

import java.util.List;

@Dao
public interface OrderDao {




    @Insert
    void insertAll(Order order);

    @Query("Select * From [Order] order by order_date")
    List<Order> getallbydate();

    @Query("Select * From [Order] where status like 'inprogessing'")
    List<Order> getallbyStatus();

    @Query("Select * From [Order] where status like 'delete' or status like 'done'")
    List<Order> getAllbyStatusHistory();

    @Query("Select * from `order` where status like 'inprogessing' and " +"( " + " total_price like '%' || :keyword || '%' or  customer_name like '%' || :keyword || '%'"+" )")
    List<Order> getOrderBysearch(String keyword);

    @Query("Select * from `order` where status like 'delete' or status like 'done'  and" +"( " + "total_price like '%' || :keyword || '%' or  customer_name like '%' || :keyword || '%' " +" )" )
    List<Order> getOrderHistoryBysearch(String keyword);

    @Query("Select * From [Order] ")
    List<Order> getall();

    @Query("Select * From [Order] order by order_id desc Limit 1")
    Order getLastOrder();

    @Query("Select count(*) from [Order] where status like 'inprogessing'")
    int countOrderInprogress();

    @Query("Select sum(total_price) from [Order] where status like 'done'")
    double sumTotalPrice();

    @Update
    void updateStatus(Order order);
}

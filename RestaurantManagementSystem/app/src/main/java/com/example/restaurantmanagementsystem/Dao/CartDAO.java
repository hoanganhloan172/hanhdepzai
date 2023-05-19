package com.example.restaurantmanagementsystem.Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.restaurantmanagementsystem.entity.Cart;

import java.util.List;

@Dao
public interface CartDAO {
//    @Query("select * from cart")
//    List<Cart> getAllCart();

    @Query("select * from cart where cart_user_id = :userId")
    List<Cart> getCartByUser(int userId);

    @Query("select * from cart where cart_item_id = :itemId")
    Cart getCartByItemId(int itemId);

    @Query("update cart set cart_quantity = cart_quantity+1 where cart_item_id = :itemId")
    void plusQuantity(int itemId);

    @Query("update cart set cart_quantity = cart_quantity-1 where cart_item_id = :itemId")
    void minusQuantity(int itemId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Cart cart);

    @Delete
    void delete(Cart cart);

    @Query("Delete from cart where cart_user_id = :userId")
    void deleteCartByUser(int userId);
}

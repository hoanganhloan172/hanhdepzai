package com.example.restaurantmanagementsystem.respository;

import android.content.Context;

import com.example.restaurantmanagementsystem.Dao.CartDAO;
import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.Cart;

import java.util.List;

public class CartRepository {
    private CartDAO cartDAO;
    public CartRepository(Context context){
        ContextDatabase itemRoomDatabase = ContextDatabase.getInstance(context);
        cartDAO = itemRoomDatabase.cartDAO();
    }

//    public List<Cart> getAllCart(){
//        return cartDAO.getAllCart();
//    }
    public List<Cart> getCartByUser(int userId){
        return cartDAO.getCartByUser(userId);
    }
    public void insertCart(Cart cart){
        cartDAO.insert(cart);
    }
    public Cart getCartByitemId(int itemId){return cartDAO.getCartByItemId(itemId);}
    public void plusQuantity(int itemId){cartDAO.plusQuantity(itemId);}
    public void minusQuantity(int itemId){cartDAO.minusQuantity(itemId);}
    public void delete(Cart cart){
        cartDAO.delete(cart);
    }
    public void deleteCartByUser(int userId){cartDAO.deleteCartByUser(userId);}
}

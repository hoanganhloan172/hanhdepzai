package com.example.restaurantmanagementsystem.respository;

import android.content.Context;

import com.example.restaurantmanagementsystem.Dao.ItemDAO;
import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.Item;

import java.util.List;

public class ItemRepository {
    private ItemDAO itemDAO;
    public ItemRepository(Context context){
        ContextDatabase itemRoomDatabase = ContextDatabase.getInstance(context);
        itemDAO = itemRoomDatabase.itemDAO();
    }
    public List<Item> getAllItem(){
        return itemDAO.getAllWords();
    }

    public List<Item> getItemForMenu(){
        return itemDAO.getItemForMenu();
    }

    public List<Item> searchItem(String name){
        return itemDAO.searchItem(name);
    }

    public void insertItem(Item item){
        itemDAO.insert(item);
    }

    public void updateItem(Item item){
        itemDAO.update(item);
    }

    public List<Item> getItemByName(String item){
        return itemDAO.select(item);
    }
}

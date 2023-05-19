package com.example.restaurantmanagementsystem.respository;

import android.content.Context;

import com.example.restaurantmanagementsystem.Dao.MenuDAO;
import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.Menu;

import java.util.List;

public class MenuRepository {
    private MenuDAO menuDAO;
    public MenuRepository(Context context){
        ContextDatabase menuRoomDatabase = ContextDatabase.getInstance(context);
        menuDAO = menuRoomDatabase.menuDAO();
    }
    public List<Menu> getAllmenu(){
        return menuDAO.getAllMenu();
    }

    public List<Menu> searchMenu(String name){
        return menuDAO.searchMenu(name);
    }

    public void insertMenu(Menu menu){
        menuDAO.insert(menu);
    }

    public void updateMenu(Menu menu){
        menuDAO.update(menu);
    }

    public List<Menu> getMenuByName(String item){
        return menuDAO.select(item);
    }
}

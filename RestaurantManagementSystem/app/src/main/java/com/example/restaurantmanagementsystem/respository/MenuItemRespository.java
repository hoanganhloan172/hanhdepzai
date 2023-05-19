package com.example.restaurantmanagementsystem.respository;

import android.content.Context;

import com.example.restaurantmanagementsystem.Dao.MenuItemDAO;
import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.MenuItem;

import java.util.List;

public class MenuItemRespository {
    private MenuItemDAO menuItemDAO;
    public MenuItemRespository(Context context){
        ContextDatabase menuItemRoomDatabase = ContextDatabase.getInstance(context);
        menuItemDAO = menuItemRoomDatabase.menuItemDAO();
    }
    public List<MenuItem> getAllMenuItem(){
        return menuItemDAO.getAllMenuItem();
    }

    public List<MenuItem> searchMenu(int menuId){
        return menuItemDAO.selectItemByMenu(menuId);
    }

    public void insertMenuItem(MenuItem menuItem){
        menuItemDAO.insert(menuItem);
    }

    public void updateMenuItem(MenuItem menuItem){
        menuItemDAO.update(menuItem);
    }

    public void deleteMenuItem(MenuItem menuItem){
        menuItemDAO.delete(menuItem);
    }

    public void deletemenuItemBy2Id(int menuId,int itemId){
        menuItemDAO.deletemenuItemBy2Id(menuId,itemId);
    }

    public void deleteMenuItemByItemId(int itemId){
        menuItemDAO.deleteMenuItemByItemId(itemId);
    }


//    public List<MenuItem> getMenuitemByName(String menuItem){
//        return menuItemDAO.select(menuItem);
//    }
}

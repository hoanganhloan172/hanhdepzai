package com.example.restaurantmanagementsystem.respository;

import android.content.Context;

import com.example.restaurantmanagementsystem.Dao.CategoryDAO;
import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.Category;

import java.util.List;

public class CategoryRepository {
    private CategoryDAO categoryDAO;
    public CategoryRepository(Context context){
        ContextDatabase itemRoomDatabase = ContextDatabase.getInstance(context);
        categoryDAO = itemRoomDatabase.categoryDAO();
    }
    public List<Category> getAllCategory(){
        return categoryDAO.getAllCategory();
    }

    public List<Category> getAllCategoryByStatus(){
        return categoryDAO.getAllCategoryByStatus();
    }

    public List<Category> searchCategory(String name){
        return categoryDAO.searchCategory(name);
    }

    public void insertCategory(Category category){
        categoryDAO.insert(category);
    }

    public void updateCategory(Category category){
        categoryDAO.update(category);
    }

    public List<Category> getCategory(String category){
        return categoryDAO.select(category);
    }
}

package com.example.restaurantmanagementsystem.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.restaurantmanagementsystem.Dao.CartDAO;
import com.example.restaurantmanagementsystem.Dao.CategoryDAO;
import com.example.restaurantmanagementsystem.Dao.ContactDao;
import com.example.restaurantmanagementsystem.Dao.EmployeeDao;
import com.example.restaurantmanagementsystem.Dao.ItemDAO;
import com.example.restaurantmanagementsystem.Dao.MealDao;
import com.example.restaurantmanagementsystem.Dao.MenuDAO;
import com.example.restaurantmanagementsystem.Dao.MenuItemDAO;
import com.example.restaurantmanagementsystem.Dao.OrderDao;
import com.example.restaurantmanagementsystem.Dao.OrderDetailDao;
import com.example.restaurantmanagementsystem.Dao.RestaurantDao;
import com.example.restaurantmanagementsystem.Dao.TableDao;
import com.example.restaurantmanagementsystem.Dao.UserDao;
import com.example.restaurantmanagementsystem.entity.Cart;
import com.example.restaurantmanagementsystem.entity.Category;
import com.example.restaurantmanagementsystem.entity.Contact;
import com.example.restaurantmanagementsystem.entity.Employee;
import com.example.restaurantmanagementsystem.entity.Item;
import com.example.restaurantmanagementsystem.entity.Meal;
import com.example.restaurantmanagementsystem.entity.Menu;
import com.example.restaurantmanagementsystem.entity.MenuItem;
import com.example.restaurantmanagementsystem.entity.Order;
import com.example.restaurantmanagementsystem.entity.OrderDetail;
import com.example.restaurantmanagementsystem.entity.Restaurant;
import com.example.restaurantmanagementsystem.entity.Table;
import com.example.restaurantmanagementsystem.entity.User;

@Database(entities = {Contact.class, Order.class, OrderDetail.class, User.class,
        Item.class, Restaurant.class, Category.class, Menu.class, MenuItem.class, Cart.class, Meal.class,
        Table.class, Employee.class}, version = 3)

public abstract class ContextDatabase extends RoomDatabase {
    private final static String DATABASE_NAME = "Project.db";
    private static ContextDatabase instance;
    public static synchronized ContextDatabase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), ContextDatabase.class,DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract ContactDao contactDao();

    public abstract OrderDao orderDao();

    public abstract OrderDetailDao orderDetailDao();

    public abstract UserDao userDao();

    public abstract CategoryDAO categoryDAO();

    public abstract ItemDAO itemDAO();

    public abstract MenuDAO menuDAO();

    public abstract MenuItemDAO menuItemDAO();

    public abstract CartDAO cartDAO();

    public abstract RestaurantDao restaurantDao();

    public abstract MealDao mealDao();

    public abstract TableDao tableDao();

    public abstract EmployeeDao employeeDao();
}

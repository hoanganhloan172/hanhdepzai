package com.example.restaurantmanagementsystem.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "menu")
public class Menu implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull private int menuId;

    @ColumnInfo(name = "menu_name")
    private String menuName;

    @ColumnInfo(name = "menu_status")
    private boolean menuStatus;

    public Menu() {
    }

    public Menu(int menuId, String menuName, boolean menuStatus) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuStatus = menuStatus;
    }

    public Menu(String menuName, boolean menuStatus) {
        this.menuName = menuName;
        this.menuStatus = menuStatus;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public boolean isMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(boolean menuStatus) {
        this.menuStatus = menuStatus;
    }
}

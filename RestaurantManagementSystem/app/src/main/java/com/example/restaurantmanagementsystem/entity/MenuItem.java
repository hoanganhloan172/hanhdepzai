package com.example.restaurantmanagementsystem.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "menu_item")
public class MenuItem {

    @PrimaryKey(autoGenerate = true)
    @NonNull private int menuItemId;

    @ColumnInfo(name = "menu_id")
    private int menuId;

    @ColumnInfo(name = "item_id")
    private int itemId;

    @ColumnInfo(name = "menu_name")
    private String menuName;

    @ColumnInfo(name = "item_name")
    private String itemName;

    public MenuItem(int menuItemId, int menuId, int itemId, String menuName, String itemName) {
        this.menuItemId = menuItemId;
        this.menuId = menuId;
        this.itemId = itemId;
        this.menuName = menuName;
        this.itemName = itemName;
    }

    public MenuItem(int menuId, int itemId, String menuName, String itemName) {
        this.menuId = menuId;
        this.itemId = itemId;
        this.menuName = menuName;
        this.itemName = itemName;
    }

    public MenuItem(int menuId, int itemId) {
        this.menuId = menuId;
        this.itemId = itemId;
    }

    public MenuItem() {
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "menuItemId=" + menuItemId +
                ", menuId=" + menuId +
                ", itemId=" + itemId +
                ", menuName='" + menuName + '\'' +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}

package com.example.restaurantmanagementsystem.entity;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart")
public class Cart {

    @PrimaryKey(autoGenerate = true)
    @NonNull private int id;

    @ColumnInfo(name = "cart_user_id")
    private int userId;

    @ColumnInfo(name = "cart_item_id")
    private int itemId;

    @ColumnInfo(name = "cart_item_name")
    private String itemName;

    @ColumnInfo(name = "cart_image")
    private String image;

    @ColumnInfo(name = "cart_price")
    private double price;

    @ColumnInfo(name = "cart_quantity")
    private int quantity;

    @ColumnInfo(name = "cart_status")
    private boolean status;


    @ColumnInfo(name = "cart_category_id")
    private int categoryId;

    @ColumnInfo(name = "cart_category_name")
    private String categoryName;

    @ColumnInfo(name = "cart_description")
    private String description;

    public Cart() {
    }

    public Cart(int id, int userId, int itemId, String itemName, String image, double price, int quantity, boolean status, int categoryId, String categoryName, String description) {
        this.id = id;
        this.userId = userId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
    }

    public Cart(int userId, int itemId, String itemName, String image, double price, int quantity, boolean status, int categoryId, String categoryName, String description) {
        this.userId = userId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", status=" + status +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

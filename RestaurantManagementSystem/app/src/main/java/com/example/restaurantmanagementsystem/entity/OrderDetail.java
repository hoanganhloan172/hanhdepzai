package com.example.restaurantmanagementsystem.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "orderdetail")
public class OrderDetail implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "order_detail_id")
    private int orderDetailId;

    @ColumnInfo(name = "order_id")
    private int orderId;
    @ColumnInfo(name = "item_id")
    private int itemId;
    @ColumnInfo(name = "quantity")
    private int quantity;
    @ColumnInfo(name = "item_price")
    private double itemPrice;
    @ColumnInfo(name = "item_name")
    private String itemName;

    public OrderDetail(int orderDetailId, int orderId, int itemId, int quantity, double itemPrice, String itemName) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
        this.itemName = itemName;
    }

    public OrderDetail() {
    }

    public OrderDetail(int orderId, int itemId, int quantity, double itemPrice) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public OrderDetail(int orderId, int itemId, int quantity, double itemPrice, String itemName) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
        this.itemName = itemName;
    }


    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailId=" + orderDetailId +
                ", orderId=" + orderId +
                ", itemId=" + itemId +
                ", quantity=" + quantity +
                ", itemPrice=" + itemPrice +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}

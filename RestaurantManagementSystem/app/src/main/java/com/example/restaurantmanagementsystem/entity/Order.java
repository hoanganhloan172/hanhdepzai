package com.example.restaurantmanagementsystem.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "order")
public class Order implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "order_id")
    private int orderId;
    @ColumnInfo(name = "order_date")

    private String orderDate;
    @ColumnInfo(name = "total_price")

    private double totalPrice;

    @ColumnInfo(name = "customer_id")
    private int customerId;


    private String status;
    @ColumnInfo(name = "customer_name")
    private String customerName;


    public Order(int orderId, String orderDate, double totalPrice, int customerId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
    }

    public Order(int orderId, String orderDate, double totalPrice, int customerId, String status) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
        this.status = status;
    }

    public Order(String orderDate, double totalPrice, int customerId, String status) {
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
        this.status = status;

    }

    public Order(int orderId, String orderDate, double totalPrice, int customerId, String status, String customerName) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
        this.status = status;
        this.customerName = customerName;
    }

    public Order(String orderDate, double totalPrice, int customerId, String status, String customerName) {
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
        this.status = status;
        this.customerName = customerName;
    }

    public Order() {
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate='" + orderDate + '\'' +
                ", totalPrice=" + totalPrice +
                ", customerId=" + customerId +
                ", status='" + status + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}

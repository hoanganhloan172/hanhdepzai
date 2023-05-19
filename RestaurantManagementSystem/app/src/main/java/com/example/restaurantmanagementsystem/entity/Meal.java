package com.example.restaurantmanagementsystem.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "meal")
public class Meal implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "meal_id")
    private int mealId;
    @ColumnInfo(name = "order_id")
    private int orderId;

    @ColumnInfo(name = "table_id")
    private int tableId;
    @ColumnInfo(name = "date_order_table")
    private String dateOrderTable;
    private String status;
    @ColumnInfo(name = "customer_name")
    private String customerName;

    public Meal() {
    }



    public Meal(int mealId, int orderId, int tableId, String dateOrderTable, String status, String customerName) {
        this.mealId = mealId;
        this.orderId = orderId;
        this.tableId = tableId;
        this.dateOrderTable = dateOrderTable;
        this.status = status;
        this.customerName = customerName;
    }

    public Meal(int orderId, int tableId, String dateOrderTable, String status, String customerName) {
        this.orderId = orderId;
        this.tableId = tableId;
        this.dateOrderTable = dateOrderTable;
        this.status = status;
        this.customerName = customerName;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }



    public String getDateOrderTable() {
        return dateOrderTable;
    }

    public void setDateOrderTable(String dateOrderTable) {
        this.dateOrderTable = dateOrderTable;
    }



    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "mealId=" + mealId +
                ", orderId=" + orderId +
                ", tableId=" + tableId +
                ", dateOrderTable='" + dateOrderTable + '\'' +
                ", status='" + status + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}

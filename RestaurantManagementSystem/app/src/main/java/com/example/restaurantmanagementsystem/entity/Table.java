package com.example.restaurantmanagementsystem.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity (tableName = "table")
public class Table implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "number_person")
    private int numberPerson;
    @ColumnInfo(name = "floor")
    private int floor;

    @ColumnInfo(name = "status")
    private String status;

    public Table() {
    }


    public Table(int id, int numberPerson, int floor, String status) {
        this.id = id;
        this.numberPerson = numberPerson;
        this.floor = floor;
        this.status = status;
    }

    public Table(int numberPerson, int floor, String status) {
        this.numberPerson = numberPerson;
        this.floor = floor;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberPerson() {
        return numberPerson;
    }

    public void setNumberPerson(int numberPerson) {
        this.numberPerson = numberPerson;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", numberPerson=" + numberPerson +
                ", floor=" + floor +
                ", status='" + status + '\'' +
                '}';
    }
}

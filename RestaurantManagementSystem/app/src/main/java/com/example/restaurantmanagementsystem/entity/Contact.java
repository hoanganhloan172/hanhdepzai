package com.example.restaurantmanagementsystem.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "contact")
public class Contact implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int contactId;

    private String Name;
    private String Message;
    private int userId;

    public Contact() {
    }

    public Contact(int contactId, String name, String message, int userId) {
        this.contactId = contactId;
        Name = name;
        Message = message;
        this.userId = userId;
    }

    public Contact(String name, String message, int userId) {
        Name = name;
        Message = message;
        this.userId = userId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", Name='" + Name + '\'' +
                ", Message='" + Message + '\'' +
                ", userId=" + userId +
                '}';
    }
}

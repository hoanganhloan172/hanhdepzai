package com.example.restaurantmanagementsystem.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "user")
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    private int userId;
    @ColumnInfo(name = "user_name")

    private String userName;
    @ColumnInfo(name = "password")

    private String password;
    @ColumnInfo(name = "email")

    private String email;
    @ColumnInfo(name = "full_name")

    private String fullName;
    @ColumnInfo(name = "mobile")

    private String mobile;
    @ColumnInfo(name = "address")

    private String address;

    @ColumnInfo(name = "role_name")

    private String roleName;


    public User(int userId, String userName, String password, String email, String fullName, String mobile, String address, String roleName) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.mobile = mobile;
        this.address = address;
        this.roleName = roleName;
    }

    public User(String userName, String password, String email, String fullName, String mobile, String address, String roleName) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.mobile = mobile;
        this.address = address;
        this.roleName = roleName;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}

package com.example.restaurantmanagementsystem.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "restaurant")

public class Restaurant implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "restaurant_id")
    private int restaurantId;
    @ColumnInfo(name = "restaurant_name")

    private String restaurantName;
    @ColumnInfo(name = "domain_name")

    private String domainName;
    @ColumnInfo(name = "phone")

    private String phone;
    @ColumnInfo(name = "street_name")

    private String streetName;
    @ColumnInfo(name = "area")

    private String area;
    @ColumnInfo(name = "city")

    private String city;

    @ColumnInfo(name = "state")

    private String state;

    @ColumnInfo(name = "country")

    private String country;

    public Restaurant(int restaurantId, String restaurantName, String domainName, String phone, String streetName, String area, String city, String state, String country) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.domainName = domainName;
        this.phone = phone;
        this.streetName = streetName;
        this.area = area;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public Restaurant(String restaurantName, String domainName, String phone, String streetName, String area, String city, String state, String country) {
        this.restaurantName = restaurantName;
        this.domainName = domainName;
        this.phone = phone;
        this.streetName = streetName;
        this.area = area;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public Restaurant() {
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Restaurant{" + "restaurantId=" + restaurantId + ", restaurantName='" + restaurantName + '\'' + ", domainName='" + domainName + '\'' + ", phone='" + phone + '\'' + ", streetName='" + streetName + '\'' + ", area='" + area + '\'' + ", city='" + city + '\'' + ", state='" + state + '\'' + ", country='" + country + '\'' + '}';
    }
}


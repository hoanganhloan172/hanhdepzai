package com.example.restaurantmanagementsystem.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity (tableName = "employee")
public class Employee  implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "employee_name")
    private String name;
    @ColumnInfo(name = "employee_dob")
    private String dob;
    @ColumnInfo(name = "employee_gender")
    private String gender;
    @ColumnInfo(name = "employee_phone")
    private String phone;
    @ColumnInfo(name = "employee_address")
    private String address;
    @ColumnInfo(name = "employee_salary")
    private String salary;
    @ColumnInfo(name = "employee_description")
    private String description;

    private String status;

    public Employee() {
    }

    public Employee(int id, String name, String dob, String gender, String phone, String address, String salary, String description, String status) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.salary = salary;
        this.description = description;
        this.status = status;
    }

    public Employee(String name, String dob, String gender, String phone, String address, String salary, String description, String status) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.salary = salary;
        this.description = description;
        this.status = status;
    }

    public Employee(int id, String name, String dob, String gender, String phone, String address, String salary, String description) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.salary = salary;
        this.description = description;
    }

    public Employee(String name, String dob, String gender, String phone, String address, String salary, String description) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.salary = salary;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", salary='" + salary + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}


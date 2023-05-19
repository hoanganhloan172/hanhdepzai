package com.example.restaurantmanagementsystem.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.restaurantmanagementsystem.entity.Employee;

import java.util.List;
@Dao
public interface EmployeeDao {
    @Insert
    void insertEmployee(Employee employee);

    @Query("SELECT * FROM employee")
    List<Employee> getListEmployee();

    @Update
    void updateEmployee(Employee employee);

    @Delete
    void deleteEmployee(Employee employee);

    @Query("SELECT * FROM employee WHERE employee_phone= :phone")
    List<Employee> checkEmployee(String phone);

}

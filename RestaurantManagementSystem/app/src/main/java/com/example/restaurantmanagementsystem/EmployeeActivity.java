package com.example.restaurantmanagementsystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.restaurantmanagementsystem.adapter.EmployeeAdapter;
import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeActivity extends AppCompatActivity {

    private static final int MY_REQUEST_EMPLOYEE = 10;
    private EmployeeAdapter employeeAdapter;
    private RecyclerView recyclerViewEmployee;
    private Button btnAddEmployee;
    private ImageView ivEditEmployee, ivDeleteEmployee;
    private List<Employee> employeeList = new ArrayList<>();

    private void initUI(){
        btnAddEmployee = findViewById(R.id.add_employee);
        recyclerViewEmployee = findViewById(R.id.rcv_employee_management);
        ivEditEmployee = findViewById(R.id.iv_edit_employee);


    }

    private void loadData(){
        employeeList= ContextDatabase.getInstance(this).employeeDao().getListEmployee();
        for(int i = 0;i<employeeList.size();i++){
            System.out.println(employeeList.get(i));
        }
        employeeAdapter.setDataEmployee(employeeList);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        initUI();

        employeeAdapter = new EmployeeAdapter(new EmployeeAdapter.ClickItemsEmployee() {
            @Override
            public void updateEmployee(Employee employee) {
                clickUpdateEmployee(employee);
            }

            @Override
            public void changeStatusEmployee(Employee employee) {
                clickchangeStatusEmployee(employee);
            }


        });
        employeeList = new ArrayList<>();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewEmployee.setLayoutManager(linearLayoutManager);


        loadData();
        recyclerViewEmployee.setAdapter(employeeAdapter);

        btnAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(EmployeeActivity.this, AddEmployeeActivity.class);
                startActivity(i);

            }
        });
    }

    private void clickchangeStatusEmployee(Employee employee){
        if(employee.getStatus().equals("active")){
            employee.setStatus("inactive");

        }else{
            employee.setStatus("active");

        }

        ContextDatabase.getInstance(this).employeeDao().updateEmployee(employee);
        loadData();

    }

    private void clickUpdateEmployee(Employee employee) {
        Intent i = new Intent(EmployeeActivity.this, EditEmployeeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_employee", employee);
        i.putExtras(bundle);
        startActivityForResult(i, MY_REQUEST_EMPLOYEE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_REQUEST_EMPLOYEE && resultCode == Activity.RESULT_OK){
            loadData();
        }
    }
}
package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.adapter.EmployeeAdapter;
import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.Employee;


import java.util.ArrayList;
import java.util.List;

public class AddEmployeeActivity extends AppCompatActivity {

    private EditText addEmployeeName, addEmployeeDob, addEmployeePhone,
            addEmployeeAddress, addEmployeeSalary, addEmployeeDescription;
    private Button btnAddNewEmployee;
    private RadioButton male, female;
    private ImageButton igbackaddemploy;
    private List<Employee> employeeList;
    private EmployeeAdapter employeeAdapter;
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        initUI();
        employeeAdapter = new EmployeeAdapter();
        employeeList = new ArrayList<>();
        employeeAdapter.setDataEmployee(employeeList);

        male.setChecked(true);

        btnAddNewEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEmployee();
            }
        });

        igbackaddemploy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddEmployeeActivity.this, EmployeeActivity.class);
                startActivity(i);
            }
        });

    }

    private void addEmployee() {
        if(CheckAllFields()){
            if(male.isChecked()){
                gender="Male";
            }
            if(female.isChecked()){
                gender="Female";
            }
            String employeeName = addEmployeeName.getText().toString().trim();
            String employeeDob = addEmployeeDob.getText().toString().trim();
            String employeeGender = gender;
            String employeePhone = addEmployeePhone.getText().toString().trim();
            String employeeAddress = addEmployeeAddress.getText().toString().trim();
            String employeeSalary = addEmployeeSalary.getText().toString().trim();
            String employeeDescription = addEmployeeDescription.getText().toString().trim();

            //Create a employee
            Employee employee = new Employee(employeeName, employeeDob, employeeGender, employeePhone, employeeAddress, employeeSalary, employeeDescription,"active");

            ContextDatabase.getInstance(this).employeeDao().insertEmployee(employee);

            Toast.makeText(AddEmployeeActivity.this, "Add employee successfully", Toast.LENGTH_SHORT).show();

            //Set text again
            addEmployeeName.setText("");
            addEmployeeDob.setText("");
            addEmployeePhone.setText("");
            addEmployeeAddress.setText("");
            addEmployeeSalary.setText("");
            addEmployeeDescription.setText("");

            hideSoftKeyBoard();
        }
    }

    private boolean CheckAllFields() {
        boolean check= true;

        if (addEmployeeName.length() == 0) {
            addEmployeeName.setError("This field is required");
            check = false;
        }

        if (addEmployeeName.length() > 50) {
            addEmployeeName.setError("Name not over 50 character");
            check = false;
        }

        if (addEmployeeDob.length() == 0) {
            addEmployeeDob.setError("This field is required");
            check = false;
        }


        if (addEmployeeSalary.length() == 0) {
            addEmployeeSalary.setError("This field is required");
            check = false;
        }


        if (addEmployeeSalary.length() > 15) {
            addEmployeeSalary.setError("This field not over 15 character");
            check = false;
        }


        if (addEmployeeDescription.length() > 100) {
            addEmployeeDescription.setError("This field not over 100 character");
            check = false;
        }

        if (addEmployeePhone.length() == 0) {
            addEmployeePhone.setError("This field is required");
            check = false;
        }

        if (addEmployeePhone.length() > 10) {
            addEmployeePhone.setError("Phone not over 10 character");
            check = false;
        }
        if (!addEmployeePhone.getText().toString().trim().matches("^[0][0-9]+$")) {
            addEmployeePhone.setError("Phone must be compatible with Vietnam's phone number");
            check = false;
        }

        if (addEmployeeAddress.length() == 0) {
            addEmployeeAddress.setError("This field is required");
            check = false;
        }

        if (addEmployeeAddress.length() > 50) {
            addEmployeeAddress.setError("This field  not over 50 character");
            check = false;
        }

        // after all validation return true.
        return check;
    }


    private void hideSoftKeyBoard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        addEmployeeName = findViewById(R.id.add_employee_name);
        addEmployeeDob = findViewById(R.id.add_employee_dob);
        male = findViewById(R.id.rdb_add_male);
        female = findViewById(R.id.rdb_add_female);
        addEmployeePhone = findViewById(R.id.add_employee_phone);
        addEmployeeAddress = findViewById(R.id.add_employee_address);
        addEmployeeSalary = findViewById(R.id.add_employee_salary);
        addEmployeeDescription = findViewById(R.id.add_employee_description);
        btnAddNewEmployee = findViewById(R.id.btn_add_new_employee);
        igbackaddemploy = findViewById(R.id.back_on_add_employ);
    }

    private boolean isEmployeeExist(Employee employee) {
        List<Employee> employeeList = ContextDatabase.getInstance(this).employeeDao().checkEmployee(employee.getPhone());
        return employeeList != null && !employeeList.isEmpty();
    }
}
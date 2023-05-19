package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.Employee;


public class EditEmployeeActivity extends AppCompatActivity {

    private EditText editEmployeeName, editEmployeeDob, editEmployeePhone,
            editEmployeeAddress, editEmployeeSalary, editEmployeeDescription;
    private Button btnSaveEditEmployee;
    private RadioButton male, female;
    private ImageButton igbackeditemploy;
    private Employee employee;
    private String gender;

    private void initUI() {
        editEmployeeName = findViewById(R.id.edit_employee_name);
        editEmployeeDob = findViewById(R.id.edit_employee_dob);
//        editEmployeeGender = findViewById(R.id.edit_employee_gender);
        editEmployeePhone = findViewById(R.id.edit_employee_phone);
        male = findViewById(R.id.rdb_edt_male);
        female = findViewById(R.id.rdb_edt_female);
        editEmployeeAddress = findViewById(R.id.edit_employee_address);
        editEmployeeSalary = findViewById(R.id.edit_employee_salary);
        editEmployeeDescription = findViewById(R.id.edit_employee_description);
        btnSaveEditEmployee = findViewById(R.id.btn_save_edit_employee);
        igbackeditemploy = findViewById(R.id.back_on_edit_employ);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee);

        initUI();

        employee = (Employee) getIntent().getExtras().get("object_employee");

        if (employee != null) {
            editEmployeeName.setText(employee.getName());
            editEmployeeDob.setText(employee.getDob());

            if(employee.getGender().equals("Male")){
                male.setChecked(true);
            }
            if(employee.getGender().equals("Female")){
                female.setChecked(true);
            }

            editEmployeePhone.setText(employee.getPhone());
            editEmployeeAddress.setText(employee.getAddress());
            editEmployeeSalary.setText(employee.getSalary());
            editEmployeeDescription.setText(employee.getDescription());
        }

        btnSaveEditEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editEmployee();
            }
        });

        igbackeditemploy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditEmployeeActivity.this, EmployeeActivity.class);
                startActivity(i);
            }
        });

    }

    private void editEmployee() {
        if(CheckAllFields()){
            if(male.isChecked()){
                gender="Male";
            }
            if(female.isChecked()){
                gender="Female";
            }
            String employeeName = editEmployeeName.getText().toString().trim();
            String employeeDob = editEmployeeDob.getText().toString().trim();
            String employeeGender = gender;
            String employeePhone = editEmployeePhone.getText().toString().trim();
            String employeeAddress = editEmployeeAddress.getText().toString().trim();
            String employeeSalary = editEmployeeSalary.getText().toString().trim();
            String employeeDescription = editEmployeeDescription.getText().toString().trim();

            employee.setName(employeeName);
            employee.setDob(employeeDob);
            employee.setGender(employeeGender);
            employee.setPhone(employeePhone);
            employee.setAddress(employeeAddress);
            employee.setSalary(employeeSalary);
            employee.setDescription(employeeDescription);

            ContextDatabase.getInstance(this).employeeDao().updateEmployee(employee);

            Toast.makeText(EditEmployeeActivity.this, "Edit employee successfully", Toast.LENGTH_SHORT).show();

            Intent i = new Intent();
            setResult(Activity.RESULT_OK, i);
            finish();
        }

    }

    private boolean CheckAllFields() {
        boolean check= true;
        if (editEmployeeName.length() == 0) {
            editEmployeeName.setError("This field is required");
            check = false;
        }

        if (editEmployeeName.length() > 50) {
            editEmployeeName.setError("Name not over 50 character");
            check = false;
        }

        if (editEmployeeDob.length() == 0) {
            editEmployeeDob.setError("This field is required");
            check = false;
        }

        if (editEmployeeSalary.length() == 0) {
            editEmployeeSalary.setError("This field is required");
            check = false;
        }

        if (editEmployeeSalary.length() > 15) {
            editEmployeeSalary.setError("This field not over 15 character");
            check = false;
        }


        if (editEmployeeDescription.length() > 100) {
            editEmployeeDescription.setError("This field not over 100 character");
            check = false;
        }

        if (editEmployeePhone.length() == 0) {
            editEmployeePhone.setError("This field is required");
            check = false;
        }

        if (editEmployeePhone.length() > 10) {
            editEmployeePhone.setError("Phone not over 10 character");
            check = false;
        }
        if (!editEmployeePhone.getText().toString().trim().matches("^[0][0-9]+$")) {
            editEmployeePhone.setError("Phone must be compatible with Vietnam's phone number");
            check = false;
        }

        if (editEmployeeAddress.length() == 0) {
            editEmployeeAddress.setError("This field is required");
            check = false;
        }

        if (editEmployeeAddress.length() > 50) {
            editEmployeeAddress.setError("This field  not over 50 character");
            check = false;
        }
        // after all validation return true.
        return check;
    }
}

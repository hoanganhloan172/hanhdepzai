package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.Table;


import java.util.List;

public class AddTableActivity extends AppCompatActivity {
    private EditText addNoOfPerson, addNoOfFloor;
    private Button btnAddTable;
    private ImageButton igBackTable;
    private List<Table> tableList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_table);

        initUI();

        btnAddTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTable();

            }
        });

        igBackTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddTableActivity.this,TableListActivity.class);
                startActivity(intent);

            }
        });
    }

    private void addTable() {
        if(CheckAllFields()){
            String noOfPerson = addNoOfPerson.getText().toString().trim();
            String noOfFloor = addNoOfFloor.getText().toString().trim();
            if (noOfPerson == null || noOfFloor == null) {
                return;
            }
            Table table = new Table(Integer.parseInt(noOfPerson), Integer.parseInt(noOfFloor), "active");


            ContextDatabase.getInstance(this).tableDao().insertTable(table);
            Toast.makeText(AddTableActivity.this, "Add Table successfully!", Toast.LENGTH_SHORT).show();
            //Set text again
            addNoOfPerson.setText("");
            addNoOfFloor.setText("");
        }
    }

    private boolean CheckAllFields() {
        boolean check= true;
        if (addNoOfPerson.length() == 0) {
            addNoOfPerson.setError("This field is required");
            check = false;
        }

        if (addNoOfPerson.length() > 5) {
            addNoOfPerson.setError("This field not over 5 character");
            check = false;
        }

        if (addNoOfFloor.length() == 0) {
            addNoOfFloor.setError("This field is required");
            check = false;
        }

        if (addNoOfFloor.length() > 5) {
            addNoOfFloor.setError("This field not over 5 character");
            check = false;
        }

        // after all validation return true.
        return check;
    }

    private void initUI() {

        addNoOfPerson = findViewById(R.id.add_no_of_person);
        addNoOfFloor = findViewById(R.id.add_no_of_floor);
        btnAddTable = findViewById(R.id.btn_add_new_table);
        igBackTable = findViewById(R.id.back_on_add_table);
    }


}
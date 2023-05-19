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


public class EditTableActivity extends AppCompatActivity {

    private EditText editNoOfFloor, editNoOfPerson;
    private Button btnSaveEditTable;
    private ImageButton igBackeditTable;
    private Table table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_table);

        initUI();

        table = (Table) getIntent().getExtras().get("object_table");

        if (table!= null)
        {
            editNoOfFloor.setText(Integer.toString(table.getFloor()));
            editNoOfPerson.setText(Integer.toString(table.getNumberPerson()));

        }

        btnSaveEditTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTable();
            }
        });

        igBackeditTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditTableActivity.this,TableListActivity.class);
                startActivity(intent);

            }
        });

    }

    private void editTable() {
        if(CheckAllFields()){
            String tableNoOfPerson = editNoOfPerson.getText().toString().trim();
            String tableNoOfFloor = editNoOfFloor.getText().toString().trim();
            if(tableNoOfPerson == null){
                return;
            }

            table.setNumberPerson(Integer.parseInt(tableNoOfPerson));
            table.setFloor(Integer.parseInt(tableNoOfFloor));
            System.out.println(table);
            ContextDatabase.getInstance(this).tableDao().updateTable(table);

            Toast.makeText(EditTableActivity.this, "Edit table successfully", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean CheckAllFields() {
        boolean check= true;
        if (editNoOfPerson.length() == 0) {
            editNoOfPerson.setError("This field is required");
            check = false;
        }

        if (editNoOfPerson.length() > 5) {
            editNoOfPerson.setError("This field not over 5 character");
            check = false;
        }

        if (editNoOfFloor.length() == 0) {
            editNoOfFloor.setError("This field is required");
            check = false;
        }

        if (editNoOfFloor.length() > 5) {
            editNoOfFloor.setError("This field not over 5 character");
            check = false;
        }

        // after all validation return true.
        return check;
    }

    private void initUI() {
        editNoOfFloor = findViewById(R.id.edit_floor);
        editNoOfPerson = findViewById(R.id.edit_no_of_person);

        btnSaveEditTable = findViewById(R.id.btn_save_edit_table);
        igBackeditTable = findViewById(R.id.back_on_edit_table);

    }
}
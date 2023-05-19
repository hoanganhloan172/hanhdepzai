package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {
    private EditText editName, editContent;
    private Button btnSave;
    List<Contact> listcontact = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        initUI();
        listcontact = ContextDatabase.getInstance(this).contactDao().getAll();
        for (int i = 0; i < listcontact.size(); i++) {
            System.out.println(listcontact.get(i));
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContact();
            }
        });
    }

    private void addContact() {
        if(CheckAllFields()){
            String name = editName.getText().toString().trim();
            String content = editContent.getText().toString().trim();

            Contact contact = new Contact(name, content,1);

            ContextDatabase.getInstance(this).contactDao().insertContact(contact);
            Toast.makeText(this, "Send Successfully", Toast.LENGTH_SHORT).show();
            editName.setText("");
            editContent.setText("");
        }

//        loadData();
    }

    private boolean CheckAllFields() {
        boolean check= true;
        if (editName.length() == 0) {
            editName.setError("This field is required");
            check = false;
        }

        if (editName.length() > 50) {
            editName.setError("Name not over 50 character");
            check = false;
        }

        if (editContent.length() == 0) {
            editContent.setError("This field is required");
            check = false;
        }

        if (editContent.length() > 200) {
            editContent.setError("Content not over 200 character");
            check = false;
        }

        // after all validation return true.
        return check;
    }

    private void initUI() {
        editName = findViewById(R.id.editname);
        editContent= findViewById(R.id.editcontent);
        btnSave = findViewById(R.id.btn_save);

    }
}
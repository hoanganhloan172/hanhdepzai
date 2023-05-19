package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.User;

public class UserProfileActivity extends AppCompatActivity {
    private TextView tv_role, tvusername, tvEmail;
    private EditText editFullname, editPhone, editAddress;
    private Button btnSaveProfile;
    private ImageView avatar, backProfile;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        initUI();

        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("User_Id",0);

        user = ContextDatabase.getInstance(this).userDao().getUserById(id);

        System.out.println(user);
        editFullname.setText(user.getFullName());
        tv_role.setText(user.getRoleName());
        tvEmail.setText(user.getEmail());
        editPhone.setText(user.getMobile());
        editAddress.setText(user.getAddress());
        tvusername.setText(user.getUserName());

        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile(user);
            }
        });


    }

    private void updateProfile(User user) {

        if(CheckAllFields()){
            String fullname = editFullname.getText().toString().trim();
            String address = editAddress.getText().toString().trim();
            String phone = editPhone.getText().toString().trim();

            user.setFullName(fullname);
            user.setAddress(address);
            user.setMobile(phone);
            ContextDatabase.getInstance(this).userDao().UpdateProfile(user);

            Toast.makeText(this, "Update Profile Successfully", Toast.LENGTH_SHORT).show();

            editFullname.setText(fullname);
            editAddress.setText(address);
            editPhone.setText(phone);
        }
    }



    private boolean CheckAllFields() {
        boolean check= true;
        if (editFullname.length() == 0) {
            editFullname.setError("This field is required");
            check = false;
        }

        if (editFullname.length() > 50) {
            editFullname.setError("Name not over 50 character");
            check = false;
        }

        if (editAddress.length() == 0) {
            editAddress.setError("This field is required");
            check = false;
        }

        if (editAddress.length() > 50) {
            editAddress.setError("Address not over 50 character");
            check = false;
        }

        if (editPhone.length() == 0) {
            editPhone.setError("This field is required");
            check = false;
        }

        if (editPhone.length() > 10) {
            editPhone.setError("Phone not over 10 character");
            check = false;
        }
        if (!editPhone.getText().toString().trim().matches("^[0][0-9]+$")) {
            editPhone.setError("Phone must be compatible with Vietnam's phone number");
            check = false;
        }
        // after all validation return true.
        return check;
    }


    void initUI() {

        tv_role = findViewById(R.id.tv_role);
        tvusername = findViewById(R.id.tvusername);

        editFullname = findViewById(R.id.edit_fullname);

        tvEmail = findViewById(R.id.tv_email);

        editPhone = findViewById(R.id.edit_phone);

        editAddress = findViewById(R.id.edit_address);

        btnSaveProfile = findViewById(R.id.btnSaveProfile);

        avatar = findViewById(R.id.avatarProfile);


    }
}
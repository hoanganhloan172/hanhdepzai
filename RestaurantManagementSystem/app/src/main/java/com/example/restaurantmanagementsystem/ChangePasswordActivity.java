package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.User;

public class ChangePasswordActivity extends AppCompatActivity {
    private User user;
    private EditText edtCurrentPassword;
    private EditText edtNewPassword;
    private EditText edtConfirmNewPassword;
    private Button btnChangePassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        edtCurrentPassword = findViewById(R.id.edit_current_password);
        edtNewPassword = findViewById(R.id.edit_new_password);
        edtConfirmNewPassword = findViewById(R.id.edit_confirm_new_password);
        btnChangePassword = findViewById(R.id.btn_ChangePass);

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickChangePassWord();
            }
        });

    }

    private void onClickChangePassWord() {
        String strCurrentPassword = edtCurrentPassword.getText().toString().trim();
        String strNewPassword = edtNewPassword.getText().toString().trim();
        String strConfirmNewPassword = edtConfirmNewPassword.getText().toString().trim();
        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("User_Id",0);
        user = ContextDatabase.getInstance(this).userDao().getUserById(userId);
        if (!user.getPassword().equals(strCurrentPassword)) {
            Toast.makeText(ChangePasswordActivity.this, "Not the Same password", Toast.LENGTH_SHORT).show();
        } else if (!strNewPassword.matches(strConfirmNewPassword)) {
            Toast.makeText(ChangePasswordActivity.this, "Not matches password", Toast.LENGTH_SHORT).show();
        } else if (user.getPassword().equals(strCurrentPassword) && strNewPassword.matches(strConfirmNewPassword)) {
            user.setPassword(strNewPassword);
            ContextDatabase.getInstance(this).userDao().updatePassword(user);
            Toast.makeText(ChangePasswordActivity.this, "Update Password Successful", Toast.LENGTH_SHORT).show();
        }
    }
}
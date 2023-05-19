package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.User;

public class RegisterActivity extends AppCompatActivity {
    TextView login;
    EditText userName;
    EditText email;
    EditText confirmPassword;
    EditText password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        login = findViewById(R.id.tvsignin);
        userName = findViewById(R.id.edit_username_register);
        email = findViewById(R.id.edit_email_register);
        password = findViewById(R.id.edit_password_register);
        register = findViewById(R.id.btn_register);

        confirmPassword = findViewById(R.id.edit_confirm_password_register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkEntered()){
                    registerUser();

                }
            }
        });

    }

    private void registerUser() {
        String strUserName = userName.getText().toString().trim();
        String strPassword = password.getText().toString().trim();
        String strEmail = email.getText().toString().trim();
        String strCfPassword = confirmPassword.getText().toString().trim();

        User user = new User(strUserName, strPassword, strEmail, null, null, null, "user");
        ContextDatabase.getInstance(this).userDao().registerUser(user);
        Toast.makeText(this, "Register Successfully", Toast.LENGTH_SHORT).show();

        userName.setText("");
        password.setText("");
        email.setText("");
        confirmPassword.setText("");


    }


    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence stm = text.getText().toString();
        return TextUtils.isEmpty(stm);
    }

    boolean checkEntered() {
        boolean check = true;
        if (isEmpty(userName)) {
            userName.setError("userName is required");
            check = false;
        }
        if (isEmpty(password)) {
            password.setError("Password is required!");
            check = false;

        }
        if (isEmail(email) == false) {
            email.setError("Email Invalid. Enter valid email!");
            check = false;

        }
        if(isEmpty(confirmPassword)){
            confirmPassword.setError("confirmPassword is required");
            check = false;

        }
        if(!password.getText().toString().trim().equals(confirmPassword.getText().toString().trim())){
            confirmPassword.setError("confirmPassword must match password");
            check = false;

        }

        return check;
    }


}
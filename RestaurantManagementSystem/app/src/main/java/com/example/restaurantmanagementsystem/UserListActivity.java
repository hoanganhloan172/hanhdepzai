package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.restaurantmanagementsystem.adapter.UserListAdapter;
import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {
    private RecyclerView rcvUserList;
    private ImageView imvbackUserList;

    List<User> userList = new ArrayList<>();
    UserListAdapter userListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        rcvUserList = findViewById(R.id.rcv_userlist);



        userList = ContextDatabase.getInstance(this).userDao().getListUserRole();

        userListAdapter = new UserListAdapter(new UserListAdapter.clickUserItem() {
            @Override
            public void viewInfo(User user) {
//                clickviewInfo(user);
            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvUserList.setLayoutManager(linearLayoutManager);

        userListAdapter.setData(userList);

        rcvUserList.setAdapter(userListAdapter);


    }

//    private void clickviewInfo(User user) {
//
//        Intent intent = new Intent(UserListActivity.this,ViewUser)
//    }
}
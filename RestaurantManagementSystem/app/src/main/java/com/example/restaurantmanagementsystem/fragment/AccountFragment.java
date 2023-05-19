package com.example.restaurantmanagementsystem.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.restaurantmanagementsystem.ChangePasswordActivity;
import com.example.restaurantmanagementsystem.ContactActivity;
import com.example.restaurantmanagementsystem.EmployeeActivity;
import com.example.restaurantmanagementsystem.LoginActivity;
import com.example.restaurantmanagementsystem.UserListActivity;
import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.User;
import com.example.restaurantmanagementsystem.R;
import com.example.restaurantmanagementsystem.RestaurantDetailsActivity;
import com.example.restaurantmanagementsystem.UserProfileActivity;

public class AccountFragment extends Fragment {
    private TextView tvmyprofilelogo,tvcontactuslogo, tvrestaurantdetail, tvchangepassword, btnLogout,tvmgemployee,tvuser;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_fragment,container,false);
        tvmyprofilelogo = (TextView) view.findViewById(R.id.tv_my_profile_logo);
//        tvcontactuslogo = (TextView) view.findViewById(R.id.tv_contact_us_logo);
        tvrestaurantdetail = (TextView) view.findViewById(R.id.tv_restaurant_detail);
        tvchangepassword = (TextView) view.findViewById(R.id.tv_change_password_frag);
        tvmgemployee = (TextView) view.findViewById(R.id.tv_employee_logo);
        tvuser = (TextView) view.findViewById(R.id.tv_user_logo);
        btnLogout = (Button) view.findViewById(R.id.btn_logout_admin);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(getActivity());
            }
        });
        tvmyprofilelogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = ContextDatabase.getInstance(getActivity()).userDao().getUserById(1);

                Intent intent1 = new Intent(getActivity(), UserProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                intent1.putExtras(bundle);
                startActivityForResult(intent1,10);
            }
        });

        tvchangepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        tvmgemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EmployeeActivity.class);
                startActivity(intent);
            }
        });
        tvuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UserListActivity.class);
                startActivity(intent);
            }
        });

        tvrestaurantdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RestaurantDetailsActivity.class);
                startActivity(intent);
            }
        });

        view.setTag("fragmentAccount");

        return view;
    }

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    private void logout(FragmentActivity activity) {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}

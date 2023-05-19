package com.example.restaurantmanagementsystem.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.restaurantmanagementsystem.CategoryListActivity;
import com.example.restaurantmanagementsystem.MealListActivity;
import com.example.restaurantmanagementsystem.MenuListActivity;
import com.example.restaurantmanagementsystem.MyCustomerActivity;
import com.example.restaurantmanagementsystem.OrderActivity;
import com.example.restaurantmanagementsystem.OrderHistoryActivity;
import com.example.restaurantmanagementsystem.R;
import com.example.restaurantmanagementsystem.TableListActivity;

public class ManagerFragment extends Fragment {

    private TextView tvorderhistorylogo, tvmycustomerlogo,tvAdditionalMenulogo,tvCategorymanage,tvtablemanager,tvmealmg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manager_fragment, container, false);

        // phan cua Phuoc
        tvorderhistorylogo = (TextView) view.findViewById(R.id.tv_order_history_logo);
        tvmycustomerlogo = (TextView) view.findViewById(R.id.tv_my_customer_logo);


        tvAdditionalMenulogo = (TextView) view.findViewById(R.id.tv_additional_menu);

        tvCategorymanage = (TextView) view.findViewById(R.id.tv_category_manage);
        tvtablemanager = (TextView) view.findViewById(R.id.tvtable_logo);
        tvmealmg = (TextView) view.findViewById(R.id.tv_meal);

        tvorderhistorylogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), OrderHistoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("order", "orderhistory");
                intent1.putExtras(bundle);
                startActivityForResult(intent1, 10);
            }
        });

        tvmycustomerlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyCustomerActivity.class);
                startActivity(intent);
            }
        });

        tvtablemanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TableListActivity.class);
                startActivity(intent);
            }
        });
        tvmealmg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MealListActivity.class);
                startActivity(intent);
            }
        });

        // phan cua Binh
        tvAdditionalMenulogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MenuListActivity.class);
                startActivity(intent);
            }
        });

        tvCategorymanage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoryListActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}

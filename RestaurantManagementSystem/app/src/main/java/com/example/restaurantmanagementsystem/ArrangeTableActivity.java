package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.adapter.ArrangeTableAdapter;
import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.Meal;
import com.example.restaurantmanagementsystem.entity.Order;
import com.example.restaurantmanagementsystem.entity.Table;

import java.util.ArrayList;
import java.util.List;

public class ArrangeTableActivity extends AppCompatActivity {
    private RecyclerView rcvArrangeTableList;
    private Button btnSaveArrange;
    List<Table> tableList = new ArrayList<>();
    Table table1;

    ArrangeTableAdapter arrangeTableAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrange_table);
        rcvArrangeTableList = findViewById(R.id.rcv_arrange_table_list);
        btnSaveArrange = findViewById(R.id.btn_save_arrange);
        tableList = ContextDatabase.getInstance(this).tableDao().getListTableStatus();
        arrangeTableAdapter = new ArrangeTableAdapter(new ArrangeTableAdapter.clickItemArrangeTable() {
            @Override
            public void rdbArrange(Table table) {
                table1 = table;
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvArrangeTableList.setLayoutManager(linearLayoutManager);

        arrangeTableAdapter.setData(tableList);
        rcvArrangeTableList.setAdapter(arrangeTableAdapter);

        btnSaveArrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                arrangeTable();
            }
        });
    }

    private void arrangeTable() {
        Order order = (Order) getIntent().getExtras().get("orderarrange");
        Meal meal = new Meal(order.getOrderId(),table1.getId(),order.getOrderDate(),"waiting",order.getCustomerName()) ;
        ContextDatabase.getInstance(this).mealDao().insertMeal(meal);
        order.setStatus("done");
        ContextDatabase.getInstance(this).orderDao().updateStatus(order);
        table1.setStatus("active");
        ContextDatabase.getInstance(this).tableDao().updateTable(table1);
        Intent intent = new Intent(ArrangeTableActivity.this,MealListActivity.class);
        Toast.makeText(ArrangeTableActivity.this, "Arrange Table for Order Successfully", Toast.LENGTH_SHORT).show();
        startActivity(intent);

    }


}
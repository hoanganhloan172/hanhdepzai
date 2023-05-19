package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.restaurantmanagementsystem.adapter.MealListAdapter;
import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.Meal;
import com.example.restaurantmanagementsystem.entity.Table;

import java.util.List;

public class MealListActivity extends AppCompatActivity {
    private RecyclerView rcvMealList;
    private List<Meal> list;

    MealListAdapter mealListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_list);

        rcvMealList = findViewById(R.id.rcv_meal_list);

        list = ContextDatabase.getInstance(this).mealDao().getListMealStatus();

        mealListAdapter = new MealListAdapter(new MealListAdapter.clickItemMeal() {
            @Override
            public void btndone(Meal meal) {
                clickbtndone(meal);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        rcvMealList.setLayoutManager(linearLayoutManager);

        mealListAdapter.setData(list);
        rcvMealList.setAdapter(mealListAdapter);

    }

    private void clickbtndone(Meal meal) {

        meal.setStatus("done");
        Table table = ContextDatabase.getInstance(this).tableDao().checkTable(meal.getTableId());
        table.setStatus("inactive");
        ContextDatabase.getInstance(this).tableDao().updateTable(table);
        ContextDatabase.getInstance(this).mealDao().updateMeal(meal);
        mealListAdapter.setData(ContextDatabase.getInstance(this).mealDao().getListMealStatus());

    }
}
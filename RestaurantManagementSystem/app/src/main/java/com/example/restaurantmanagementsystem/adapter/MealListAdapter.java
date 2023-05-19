package com.example.restaurantmanagementsystem.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagementsystem.entity.Meal;
import com.example.restaurantmanagementsystem.R;

import java.util.ArrayList;
import java.util.List;

public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.MealListViewHolder> {
    private clickItemMeal clickItemMeal;
    List<Meal> list = new ArrayList<>();

    public interface clickItemMeal {
        void btndone(Meal meal);
    }

    public MealListAdapter(MealListAdapter.clickItemMeal clickItemMeal) {
        this.clickItemMeal = clickItemMeal;
    }

    public void setData(List<Meal> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MealListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal, parent, false);

        return new MealListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealListViewHolder holder, int position) {
        Meal meal = list.get(position);
        System.out.println(meal);
        holder.tvCustomerNameMeal.setText(meal.getCustomerName());
        holder.tvTableMeal.setText(Integer.toString(meal.getTableId()));
        holder.tvOrderDateMeal.setText(meal.getDateOrderTable());
        holder.btnDoneMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemMeal.btndone(meal);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MealListViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCustomerNameMeal, tvTableMeal, tvOrderDateMeal;
        private Button btnDoneMeal;

        public MealListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCustomerNameMeal = itemView.findViewById(R.id.tvcustomerName_meal);
            tvTableMeal = itemView.findViewById(R.id.tvtable_meal);
            tvOrderDateMeal = itemView.findViewById(R.id.tvorderdate_meal);
            btnDoneMeal = itemView.findViewById(R.id.btn_done_meal);

        }
    }
}

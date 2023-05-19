package com.example.restaurantmanagementsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagementsystem.R;
import com.example.restaurantmanagementsystem.entity.Category;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryListViewHolder> {
    private Context context;
    private List<Category> categoriesList;

    private IClickItemCategory iClickItemCategory;

    public interface IClickItemCategory{
        void updateCategory(Category category);
        void changeStatusCategory(Category category);
    }

    public CategoryListAdapter(IClickItemCategory iClickItemCategory) {
        this.iClickItemCategory = iClickItemCategory;
    }


    public void setData(List<Category> categories){
        this.categoriesList = categories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Load layout for one word item
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_category,parent,false);
        return new CategoryListViewHolder(viewItem);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListViewHolder holder, int position) {
        // Get data from wordlist
        Category category = categoriesList.get(position);
        if(category==null){
            return;
        }
        //set data to one item
        holder.categoryName.setText(category.getCategoryName());
        holder.categoryStatus.setChecked(category.isCategoryStatus());
        holder.categoryIconEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemCategory.updateCategory(category);
            }
        });
        holder.categoryStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                iClickItemCategory.changeStatusCategory(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    protected class CategoryListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView categoryName;
        private CategoryListAdapter categoryListAdapter;
        private ImageButton categoryIconEdit;
        private Switch categoryStatus;
        public CategoryListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.categoryName = itemView.findViewById(R.id.category_name);
            this.categoryIconEdit = itemView.findViewById(R.id.category_icon_edit);
            this.categoryStatus = itemView.findViewById(R.id.category_status);

        }


        @Override
        public void onClick(View v){
//            textViewItem.setText("Clicked?" + textViewItem.getText());
        }
    }



}

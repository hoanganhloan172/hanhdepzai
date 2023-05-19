package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.entity.Category;
import com.example.restaurantmanagementsystem.entity.Item;
import com.example.restaurantmanagementsystem.respository.CategoryRepository;

import java.util.List;

public class AddCategoryActivity extends AppCompatActivity {

    private EditText addCategoryName,addCategoryDescription;

    private CategoryRepository categoryRepository = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        categoryRepository = new CategoryRepository(this);

        Button save = findViewById(R.id.save_add_category);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCategoryName = findViewById(R.id.add_category_name);
                addCategoryDescription = findViewById(R.id.add_category_description);

                String name = addCategoryName.getText().toString();
                String description = addCategoryDescription.getText().toString();

                if(CheckAllFields(name)){
                    Category category = new Category(name,true,description);

                    categoryRepository.insertCategory(category);

                    Toast.makeText(AddCategoryActivity.this, "Add successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddCategoryActivity.this,CategoryListActivity.class);
                    startActivity(intent);


                }else{
                    Toast.makeText(AddCategoryActivity.this, "Category Name can not null",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageButton btnBack = findViewById(R.id.back_on_add_cate);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCategoryActivity.this,CategoryListActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean CheckAllFields(String name) {
        boolean check= true;
        if (addCategoryName.length() == 0) {
            addCategoryName.setError("This field is required");
            check = false;
        }

        if (addCategoryName.length() > 50) {
            addCategoryName.setError("Name not over 50 character");
            check = false;
        }

        if(checkCategoryExit(name)){
            addCategoryName.setError("Item Exits");
            check = false;
        }

        if (addCategoryDescription.length() > 100) {
            addCategoryDescription.setError("Description not over 100 character");
            check = false;
        }

        // after all validation return true.
        return check;
    }

    private boolean checkCategoryExit(String categoryName){
        List<Category> category = categoryRepository.getCategory(categoryName);
        return category!=null && !category.isEmpty();
    }
}
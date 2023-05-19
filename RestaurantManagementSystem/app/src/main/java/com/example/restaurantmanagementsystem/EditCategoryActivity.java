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

public class EditCategoryActivity extends AppCompatActivity {

    private EditText edtCategoryName,edtCategoryDescription;

    private CategoryRepository categoryRepository = null;

    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        categoryRepository = new CategoryRepository(this);

        edtCategoryName = findViewById(R.id.edt_category_name);
        edtCategoryDescription = findViewById(R.id.edt_category_description);

        //get item from list
        category = (Category) getIntent().getExtras().get("object_category");

        if(category!=null){
            edtCategoryName.setText(category.getCategoryName());
            edtCategoryDescription.setText(category.getDescription());
//            System.out.println(item);
        }

        Button save = findViewById(R.id.save_edt_category);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtCategoryName = findViewById(R.id.edt_category_name);
                edtCategoryDescription = findViewById(R.id.edt_category_description);

                String name = edtCategoryName.getText().toString();
                String description = edtCategoryDescription.getText().toString();

                if(CheckAllFields(name)){
                    category.setCategoryName(name);
                    category.setDescription(description);

                    categoryRepository.updateCategory(category);

                    Toast.makeText(EditCategoryActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(EditCategoryActivity.this,CategoryListActivity.class);
                    startActivity(intent);


                }else{
                    Toast.makeText(EditCategoryActivity.this, "Category Name can not null",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageButton btnBack = findViewById(R.id.back_on_edt_cate);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditCategoryActivity.this,CategoryListActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean CheckAllFields(String name) {
        boolean check= true;
        if (edtCategoryName.length() == 0) {
            edtCategoryName.setError("This field is required");
            check = false;
        }

        if (edtCategoryName.length() > 50) {
            edtCategoryName.setError("Name not over 50 character");
            check = false;
        }

        if (edtCategoryDescription.length() > 100) {
            edtCategoryDescription.setError("Description not over 100 character");
            check = false;
        }

        // after all validation return true.
        return check;
    }
}
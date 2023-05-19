package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.adapter.CategoryListAdapter;
import com.example.restaurantmanagementsystem.entity.Category;

import com.example.restaurantmanagementsystem.respository.CategoryRepository;


import java.util.ArrayList;
import java.util.List;

public class CategoryListActivity extends AppCompatActivity {

    private static final int CATEGORY_REQUEST = 22;

    private List<Category> categoryList = new ArrayList<>();
    private CategoryRepository categoryRepository = null;
    private CategoryListAdapter categoryListAdapter;
    private EditText searchCategory;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        categoryListAdapter = new CategoryListAdapter(new CategoryListAdapter.IClickItemCategory() {
            @Override
            public void updateCategory(Category category) {
                clickUpdateCategory(category);
            }

            @Override
            public void changeStatusCategory(Category category) {
                clickChangeStatus(category);
            }
        });
        recyclerView = findViewById(R.id.recycle_view_category);
//        ItemListAdapter itemListAdapter = new ItemListAdapter(this, getItemList());
        categoryListAdapter.setData(new ArrayList<>());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(categoryListAdapter);

        ImageButton btnAddCategory = findViewById(R.id.btn_add_category);
        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryListActivity.this,AddCategoryActivity.class);
                startActivity(intent);
            }
        });
        searchCategory = findViewById(R.id.search_category);
        searchCategory.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEARCH){
                    handleSearchCategory();
                }
                return false;
            }
        });

        loadData();

    }

    private void loadData(){
        categoryListAdapter.setData(getCategoryList());
    }

    private List getCategoryList(){

        categoryRepository = new CategoryRepository(this);

        List<Category> itemListCategory = categoryRepository.getAllCategory();
        if(itemListCategory == null || itemListCategory.size()==0) {
            Category category = new Category("bun",true);
            Category category1 = new Category("com",true);
            Category category2 = new Category("pho",true);
            Category category3 = new Category("chao",false);
            categoryRepository.insertCategory(category);
            categoryRepository.insertCategory(category1);
            categoryRepository.insertCategory(category2);
            categoryRepository.insertCategory(category3);
        }

        itemListCategory = categoryRepository.getAllCategory();
        for(int i = 0; i <itemListCategory.size();i++){
            categoryList.add(itemListCategory.get(i));
        }
        return categoryList;
    }

    private void clickChangeStatus(Category category){

        categoryRepository = new CategoryRepository(this);

        if(category.isCategoryStatus()) {
            category.setCategoryStatus(false);
            categoryRepository.updateCategory(category);
        }else{
            category.setCategoryStatus(true);
            categoryRepository.updateCategory(category);
        }
        Toast.makeText(CategoryListActivity.this, "Change status successfully",
                Toast.LENGTH_SHORT).show();

    }

    private void handleSearchCategory(){
        categoryRepository = new CategoryRepository(this);
        String strKeyword = searchCategory.getText().toString().trim();
        categoryList = new ArrayList<>();
        categoryList = categoryRepository.searchCategory(strKeyword);
        categoryListAdapter.setData(categoryList);
    }

    private void clickUpdateCategory(Category category){
        Intent intent = new Intent(CategoryListActivity.this,EditCategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_category",category);
        intent.putExtras(bundle);
        startActivityForResult(intent,CATEGORY_REQUEST);
    }
}
package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.entity.Category;
import com.example.restaurantmanagementsystem.entity.Item;
import com.example.restaurantmanagementsystem.respository.CategoryRepository;
import com.example.restaurantmanagementsystem.respository.ItemRepository;

import java.util.ArrayList;
import java.util.List;

public class AddItemActivity extends AppCompatActivity {

    private EditText addItemName,addItemPrice,addItemDescription;
    //    private EditText addItemQuantity;
    private Spinner spinnerCategory;

    private ItemRepository itemRepository = null;
    private CategoryRepository categoryRepository = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        itemRepository = new ItemRepository(this);
        categoryRepository = new CategoryRepository(this);

        List<Category> itemListCategory = categoryRepository.getAllCategoryByStatus();
        ArrayList<String> listCategoryName = new ArrayList<String>();

        for(int i = 0; i <itemListCategory.size();i++){
            listCategoryName.add(itemListCategory.get(i).getCategoryName());
        }

        spinnerCategory = (Spinner)findViewById(R.id.spinner_category);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listCategoryName);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(arrayAdapter);
//        spinnerCategory.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


        Button save = findViewById(R.id.save_add_item);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemName = findViewById(R.id.add_item_name);
                addItemPrice = findViewById(R.id.add_item_price);
//                addItemQuantity = findViewById(R.id.add_item_quantity);
                addItemDescription = findViewById(R.id.add_item_description);

                String name = addItemName.getText().toString();



                if(CheckAllFields(name)){
                    String description = addItemDescription.getText().toString();

                    String category = spinnerCategory.getSelectedItem().toString();
                    List<Category> itemListCategory = categoryRepository.getCategory(category);
                    int catId = itemListCategory.get(0).getCategoryId();
//                    int quantity = Integer.parseInt(addItemQuantity.getText().toString());
                    double price = Double.parseDouble(addItemPrice.getText().toString());
                    Item item = new Item(name,price,1,true,catId,category,description);

                    itemRepository.insertItem(item);

                    Toast.makeText(AddItemActivity.this, "Add successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddItemActivity.this,ItemListActivity.class);
                    startActivity(intent);


                }else{
                    Toast.makeText(AddItemActivity.this, "Item Name and Price can not null",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageButton btnBack = findViewById(R.id.back_on_add_item);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddItemActivity.this,ItemListActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean CheckAllFields(String name) {
        boolean check= true;
        if (addItemName.length() == 0) {
            addItemName.setError("This field is required");
            check = false;
        }

        if (addItemName.length() > 50) {
            addItemName.setError("Name not over 50 character");
            check = false;
        }

        if(checkItemExit(name)){
            addItemName.setError("Item Exits");
            check = false;
        }

        if (addItemPrice.length() == 0) {
            addItemPrice.setError("This field is required");
            check = false;
        }

        if (addItemPrice.length() > 10) {
            addItemPrice.setError("Price not over 10 character");
            check = false;
        }

//        if (addItemQuantity.length() == 0) {
//            addItemQuantity.setError("This field is required");
//            check = false;
//        }
//
//        if (addItemQuantity.length() > 10) {
//            addItemQuantity.setError("Quantity not over 10 character");
//            check = false;
//        }

        if (addItemDescription.length() > 100) {
            addItemDescription.setError("Description not over 100 character");
            check = false;
        }

        // after all validation return true.
        return check;
    }

    private boolean checkItemExit(String itemName){
        List<Item> list = itemRepository.getItemByName(itemName);
        return list!=null && !list.isEmpty();
    }

}
package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class EditItemActivity extends AppCompatActivity {

    private EditText edtItemName,edtItemPrice,edtItemDescription;
    //    private EditText edtItemQuantity;
    private Spinner spinnerCategoryEdt;

    private ItemRepository itemRepository = null;
    private CategoryRepository categoryRepository = null;

    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        edtItemName = findViewById(R.id.edt_item_name);
        edtItemPrice = findViewById(R.id.edt_item_price);
//        edtItemQuantity = findViewById(R.id.edt_item_quantity);
        edtItemDescription = findViewById(R.id.edt_item_description);
        categoryRepository = new CategoryRepository(this);

        itemRepository = new ItemRepository(this);

        //get item from list
        item = (Item) getIntent().getExtras().get("object_item");

        List<Category> itemListCategory = categoryRepository.getAllCategoryByStatus();
        ArrayList<String> listCategoryName = new ArrayList<String>();

        int indexSpin = 0;
        for(int i = 0; i <itemListCategory.size();i++){
            listCategoryName.add(itemListCategory.get(i).getCategoryName());
            if(itemListCategory.get(i).getCategoryName().equals(item.getCategoryName())){
                indexSpin = i;
            }
        }

        spinnerCategoryEdt = (Spinner)findViewById(R.id.spinner_category_edt);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listCategoryName);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoryEdt.setAdapter(arrayAdapter);


        if(item!=null){
            edtItemName.setText(item.getItemName());
            edtItemPrice.setText(String.valueOf(item.getPrice()));
//            edtItemQuantity.setText(String.valueOf(item.getQuantity()));
            edtItemDescription.setText(item.getDescription());
            spinnerCategoryEdt.setSelection(indexSpin);
//            System.out.println(item);
        }

        Button saveEdtItem = findViewById(R.id.save_edt_item);
        saveEdtItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtItemName = findViewById(R.id.edt_item_name);
                edtItemPrice = findViewById(R.id.edt_item_price);
//                edtItemQuantity = findViewById(R.id.edt_item_quantity);
                edtItemDescription = findViewById(R.id.edt_item_description);

                String name = edtItemName.getText().toString();


                if(CheckAllFields()){
                    String description = edtItemDescription.getText().toString();

                    String category = spinnerCategoryEdt.getSelectedItem().toString();
                    List<Category> itemListCategory = categoryRepository.getCategory(category);
                    int catId = itemListCategory.get(0).getCategoryId();
//                    int quantity = Integer.parseInt(edtItemQuantity.getText().toString());
                    double price = Double.parseDouble(edtItemPrice.getText().toString());
                    item.setItemName(name);
                    item.setPrice(price);
//                    item.setQuantity(quantity);
                    item.setCategoryId(catId);
                    item.setCategoryName(category);
                    item.setDescription(description);

                    itemRepository.updateItem(item);

                    Toast.makeText(EditItemActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(EditItemActivity.this,ItemListActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(EditItemActivity.this, "Item Name and Price can not null",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageButton btnBack = findViewById(R.id.back_on_edt_item);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditItemActivity.this,ItemListActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean CheckAllFields() {
        boolean check= true;
        if (edtItemName.length() == 0) {
            edtItemName.setError("This field is required");
            check = false;
        }

        if (edtItemName.length() > 50) {
            edtItemName.setError("Name not over 50 character");
            check = false;
        }

        if (edtItemPrice.length() == 0) {
            edtItemPrice.setError("This field is required");
            check = false;
        }

        if (edtItemPrice.length() > 10) {
            edtItemPrice.setError("Price not over 10 character");
            check = false;
        }

//        if (edtItemQuantity.length() == 0) {
//            edtItemQuantity.setError("This field is required");
//            check = false;
//        }
//
//        if (edtItemQuantity.length() > 10) {
//            edtItemQuantity.setError("Quantity not over 10 character");
//            check = false;
//        }

        if (edtItemDescription.length() > 100) {
            edtItemDescription.setError("Description not over 100 character");
            check = false;
        }

        // after all validation return true.
        return check;
    }

}
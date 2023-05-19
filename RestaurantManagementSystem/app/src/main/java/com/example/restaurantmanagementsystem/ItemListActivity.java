package com.example.restaurantmanagementsystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.adapter.ItemListAdapter;
import com.example.restaurantmanagementsystem.entity.Category;
import com.example.restaurantmanagementsystem.entity.Item;
import com.example.restaurantmanagementsystem.respository.CategoryRepository;
import com.example.restaurantmanagementsystem.respository.ItemRepository;
import com.example.restaurantmanagementsystem.respository.MenuItemRespository;

import java.util.ArrayList;
import java.util.List;

public class ItemListActivity extends AppCompatActivity {

    private static final int ITEM_REQUEST = 20;

    private List<Item> itemList = new ArrayList<>();
    private ItemRepository itemRepository = null;
    private CategoryRepository categoryRepository = null;
    private MenuItemRespository menuItemRespository = null;
    private ItemListAdapter itemListAdapter;
    private EditText searchItem;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        itemListAdapter = new ItemListAdapter(new ItemListAdapter.IClickItemItem() {
            @Override
            public void updateItem(Item item) {
                clickUpdateItem(item);
            }

            @Override
            public void changeStatusItem(Item item) {
                clickChangeStatus(item);
            }
        });

        recyclerView = findViewById(R.id.recycle_view_item);
//        ItemListAdapter itemListAdapter = new ItemListAdapter(this, getItemList());
        itemListAdapter.setData(new ArrayList<>());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(itemListAdapter);

        //To add activity
        ImageButton btnAddItem = findViewById(R.id.btn_add_item);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemListActivity.this,AddItemActivity.class);
                startActivity(intent);
            }
        });
        searchItem = findViewById(R.id.search_item);
        searchItem.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEARCH){
                    handleSearchItem();
                }
                return false;
            }
        });

        loadData();

    }

    private void loadData(){
        itemListAdapter.setData(getItemList());
    }

    private List getItemList(){

        itemRepository = new ItemRepository(this);
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

        List<Item> itemListItem = itemRepository.getAllItem();
        if(itemListItem == null || itemListItem.size()==0) {
            Item item = new Item("Bun Bo",12500,12,true,1,"bun","ngon");
            Item item2 = new Item("Bun Dau",10500,15,true,1,"bun","ngon");
            itemRepository.insertItem(item);
            itemRepository.insertItem(item2);
        }

        itemListItem = itemRepository.getAllItem();
        for(int i = 0; i <itemListItem.size();i++){
            itemList.add(itemListItem.get(i));
            System.out.println(itemListItem.get(i));
        }
        return itemList;
    }

    private void clickUpdateItem(Item item){
        Intent intent = new Intent(ItemListActivity.this,EditItemActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_item",item);
        intent.putExtras(bundle);
        startActivityForResult(intent,ITEM_REQUEST);
    }

    private void clickChangeStatus(Item item){

        itemRepository = new ItemRepository(this);
        menuItemRespository = new MenuItemRespository(this);

        if(item.isStatus()) {
            item.setStatus(false);
            itemRepository.updateItem(item);
//                            menuItemRespository.deleteMenuItemByItemId(item.getItemId());
        }else{
            item.setStatus(true);
            itemRepository.updateItem(item);
        }
        Toast.makeText(ItemListActivity.this, "Change status successfully",
                Toast.LENGTH_SHORT).show();

//        loadData();
    }

    private void handleSearchItem(){
        itemRepository = new ItemRepository(this);
        String strKeyword = searchItem.getText().toString().trim();
        itemList = new ArrayList<>();
        itemList = itemRepository.searchItem(strKeyword);
        itemListAdapter.setData(itemList);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == ITEM_REQUEST && requestCode == Activity.RESULT_OK){
//            itemListAdapter.setData(getItemList());
//            recyclerView.setAdapter(itemListAdapter);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        }
//    }
}
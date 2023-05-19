package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.adapter.ItemInMenuAdapter;
import com.example.restaurantmanagementsystem.adapter.ItemListAdapter;
import com.example.restaurantmanagementsystem.entity.Category;
import com.example.restaurantmanagementsystem.entity.Item;
import com.example.restaurantmanagementsystem.entity.Menu;
import com.example.restaurantmanagementsystem.entity.MenuItem;
import com.example.restaurantmanagementsystem.respository.CategoryRepository;
import com.example.restaurantmanagementsystem.respository.ItemRepository;
import com.example.restaurantmanagementsystem.respository.MenuItemRespository;
import com.example.restaurantmanagementsystem.respository.MenuRepository;

import java.util.ArrayList;
import java.util.List;

public class AddMenuActivity extends AppCompatActivity {

    private List<Item> itemList = new ArrayList<>();
    private List<Item> itemListSave = new ArrayList<>();
    private ItemRepository itemRepository = null;
    private MenuRepository menuRepository = null;
    private MenuItemRespository menuItemRespository = null;
    private ItemInMenuAdapter itemInMenuAdapter;
    RecyclerView recyclerView;

    private EditText menuName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        menuRepository = new MenuRepository(this);
        menuItemRespository = new MenuItemRespository(this);


        itemInMenuAdapter = new ItemInMenuAdapter(new ItemInMenuAdapter.IClickItemInMenu() {
            @Override
            public void choiceItem(Item item) {
                handleChoiceItem(item);
            }

            @Override
            public void notChoiceItem(Item item) {
                handleNotChoiceItem(item);
            }
        });

        recyclerView = findViewById(R.id.recycle_view_item_in_menu);
//        ItemListAdapter itemListAdapter = new ItemListAdapter(this, getItemList());
        itemInMenuAdapter.setData(new ArrayList<>(),new ArrayList<>());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(itemInMenuAdapter);

        loadData();

        Button save = findViewById(R.id.save_add_menu);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuName = findViewById(R.id.add_menu_name);

                String mName = menuName.getText().toString();
                if (CheckAllFields(mName)){

                    Menu menu = new Menu(mName, true);
                    menuRepository.insertMenu(menu);

                    List<Menu> listMenu = menuRepository.getMenuByName(mName);
                    Menu menu1 = listMenu.get(0);

                    if(itemListSave.size()>0 && itemListSave!=null) {
                        for (int i = 0; i < itemListSave.size(); i++) {
                            MenuItem m1 = new MenuItem(menu1.getMenuId(), itemListSave.get(i).getItemId());
                            menuItemRespository.insertMenuItem(m1);
                        }
                    }
                    Toast.makeText(AddMenuActivity.this, "Add successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddMenuActivity.this, MenuListActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(AddMenuActivity.this, "Menu Name can not null",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageButton btnBack = findViewById(R.id.back_on_add_menu);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddMenuActivity.this,MenuListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadData(){
        itemInMenuAdapter.setData(getItemList(),new ArrayList<>());
    }

    private List getItemList(){
        itemRepository = new ItemRepository(this);
        List<Item> itemListItem = itemRepository.getItemForMenu();
        for(int i = 0; i <itemListItem.size();i++){
            itemList.add(itemListItem.get(i));
        }
        return itemList;
    }

    private boolean CheckAllFields(String name) {
        boolean check= true;
        if (menuName.length() == 0) {
            menuName.setError("This field is required");
            check = false;
        }

        if (menuName.length() > 50) {
            menuName.setError("Name not over 50 character");
            check = false;
        }

        if (checkMenuExit(name)) {
            menuName.setError("Menu Exits");
            check = false;
        }


        // after all validation return true.
        return check;
    }

    private void handleChoiceItem(Item item){
        itemListSave.add(item);
    }

    private void handleNotChoiceItem(Item item){
        itemListSave.remove(item);
    }

    private boolean checkMenuExit(String menuName){
        List<Menu> list = menuRepository.getMenuByName(menuName);
        return list!=null && !list.isEmpty();
    }


}
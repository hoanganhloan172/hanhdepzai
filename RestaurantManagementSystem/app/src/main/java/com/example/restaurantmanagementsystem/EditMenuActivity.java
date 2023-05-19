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
import com.example.restaurantmanagementsystem.entity.Item;
import com.example.restaurantmanagementsystem.entity.Menu;
import com.example.restaurantmanagementsystem.entity.MenuItem;
import com.example.restaurantmanagementsystem.respository.ItemRepository;
import com.example.restaurantmanagementsystem.respository.MenuItemRespository;
import com.example.restaurantmanagementsystem.respository.MenuRepository;

import java.util.ArrayList;
import java.util.List;

public class EditMenuActivity extends AppCompatActivity {

    private List<Item> itemList = new ArrayList<>();
    private List<Item> itemListSave = new ArrayList<>();
    private List<Item> itemListUnSave = new ArrayList<>();
    private ItemRepository itemRepository = null;
    private MenuRepository menuRepository = null;
    private MenuItemRespository menuItemRespository = null;
    private ItemInMenuAdapter itemInMenuAdapter;
    RecyclerView recyclerView;
    private Menu menu;
    private EditText edtMenuName;

    private EditText menuNameEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);

        menuRepository = new MenuRepository(this);
        menuItemRespository = new MenuItemRespository(this);

        edtMenuName = findViewById(R.id.edt_menu_name);
        //get item from list
        menu = (Menu) getIntent().getExtras().get("object_menu");

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

        recyclerView = findViewById(R.id.recycle_view_item_in_menu_edt);
//        ItemListAdapter itemListAdapter = new ItemListAdapter(this, getItemList());
        itemInMenuAdapter.setData(new ArrayList<>(),new ArrayList<>());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(itemInMenuAdapter);
        loadData(menu);

        if(menu!=null){
            edtMenuName.setText(menu.getMenuName());
        }

        Button save = findViewById(R.id.save_edt_menu);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuNameEdt = findViewById(R.id.edt_menu_name);

                String mName = menuNameEdt.getText().toString();
                if (CheckAllFields(mName)){

                    menu.setMenuName(mName);
                    menuRepository.updateMenu(menu);
                    if(itemListUnSave.size()>0 && itemListUnSave!=null) {
                        for (int i = 0; i < itemListUnSave.size(); i++) {
                            menuItemRespository.deletemenuItemBy2Id(menu.getMenuId(), itemListUnSave.get(i).getItemId());
                            System.out.println(itemListUnSave.get(i) + "a");
                        }
                    }
                    if(itemListSave.size()>0 && itemListSave!=null) {
                        for (int i = 0; i < itemListSave.size(); i++) {
                            MenuItem m1 = new MenuItem(menu.getMenuId(), itemListSave.get(i).getItemId());
                            menuItemRespository.insertMenuItem(m1);
                        }
                    }
                    Toast.makeText(EditMenuActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(EditMenuActivity.this, MenuListActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(EditMenuActivity.this, "Menu Name can not null",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageButton btnBack = findViewById(R.id.back_on_edt_menu);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditMenuActivity.this,MenuListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadData(Menu menu){
        itemInMenuAdapter.setData(getItemList(),getItemListChoice(menu));
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
        if (menuNameEdt.length() == 0) {
            menuNameEdt.setError("This field is required");
            check = false;
        }

        if (menuNameEdt.length() > 50) {
            menuNameEdt.setError("Name not over 50 character");
            check = false;
        }

        // after all validation return true.
        return check;
    }

    private List getItemListChoice(Menu menu){
        menuItemRespository = new MenuItemRespository(this);

        //test
        List<MenuItem> lm = menuItemRespository.searchMenu(menu.getMenuId());
        return lm;
    }

    private void handleChoiceItem(Item item){
        itemListSave.add(item);
    }

    private void handleNotChoiceItem(Item item){
        itemListSave.remove(item);
        itemListUnSave.add(item);
    }
}
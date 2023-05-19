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

import com.example.restaurantmanagementsystem.adapter.ItemListAdapter;
import com.example.restaurantmanagementsystem.adapter.MenuListAdapter;
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

public class MenuListActivity extends AppCompatActivity {

    private static final int MENU_REQUEST = 21;

    private List<Menu> menuList = new ArrayList<>();
    private MenuRepository menuRepository = null;
    private MenuListAdapter menuListAdapter;
    private EditText searchMenu;
    RecyclerView recyclerView;
    private MenuItemRespository menuItemRespository = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        menuItemRespository = new MenuItemRespository(this);

        //test
        List<MenuItem> lm = menuItemRespository.getAllMenuItem();
        for(int i = 0; i <lm.size();i++){
            System.out.println(lm.get(i));;
        }
        //

        menuListAdapter = new MenuListAdapter(new MenuListAdapter.IClickMenu() {
            @Override
            public void updateMenu(Menu menu) {
                clickUpdateMenu(menu);
            }

            @Override
            public void changeStatusMenu(Menu menu) {
                clickChangeStatus(menu);
            }
        });

        recyclerView = findViewById(R.id.recycle_view_menu);
//        ItemListAdapter itemListAdapter = new ItemListAdapter(this, getItemList());
        menuListAdapter.setData(new ArrayList<>());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(menuListAdapter);

        //To add activity
        ImageButton btnAddItem = findViewById(R.id.btn_add_menu);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuListActivity.this,AddMenuActivity.class);
                startActivity(intent);
            }
        });
        searchMenu = findViewById(R.id.search_menu);
        searchMenu.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEARCH){
                    handleSearchmenu();
                }
                return false;
            }
        });

        loadData();
    }

    private void loadData(){
        menuListAdapter.setData(getMenuList());
    }

    private List getMenuList(){

        menuRepository = new MenuRepository(this);

        List<Menu> itemListMenu = menuRepository.getAllmenu();
        if(itemListMenu == null || itemListMenu.size()==0) {
            Menu menu = new Menu("menu 1",true);
            Menu menu2 = new Menu("menu 2",true);
            menuRepository.insertMenu(menu);
            menuRepository.insertMenu(menu2);
        }

        itemListMenu = menuRepository.getAllmenu();
        for(int i = 0; i <itemListMenu.size();i++){
            menuList.add(itemListMenu.get(i));
        }
        return menuList;
    }
    private void clickChangeStatus(Menu menu){

        menuRepository = new MenuRepository(this);

        if(menu.isMenuStatus()) {
            menu.setMenuStatus(false);
            menuRepository.updateMenu(menu);
        }else{
            menu.setMenuStatus(true);
            menuRepository.updateMenu(menu);
        }
        Toast.makeText(MenuListActivity.this, "Change status successfully",
                Toast.LENGTH_SHORT).show();

    }

    private void handleSearchmenu(){
        menuRepository = new MenuRepository(this);
        String strKeyword = searchMenu.getText().toString().trim();
        menuList = new ArrayList<>();
        menuList = menuRepository.searchMenu(strKeyword);
        menuListAdapter.setData(menuList);
    }

    private void clickUpdateMenu(Menu menu){
        Intent intent = new Intent(MenuListActivity.this,EditMenuActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_menu",menu);
        intent.putExtras(bundle);
        startActivityForResult(intent,MENU_REQUEST);
    }


}
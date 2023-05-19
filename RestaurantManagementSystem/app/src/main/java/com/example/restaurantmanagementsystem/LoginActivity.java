package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.Category;
import com.example.restaurantmanagementsystem.entity.Item;
import com.example.restaurantmanagementsystem.entity.Order;
import com.example.restaurantmanagementsystem.entity.OrderDetail;
import com.example.restaurantmanagementsystem.entity.Table;
import com.example.restaurantmanagementsystem.entity.User;
import com.example.restaurantmanagementsystem.respository.CategoryRepository;
import com.example.restaurantmanagementsystem.respository.ItemRepository;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private static final int MY_REQUEST_CODE = 10;
    EditText email;
    EditText password;
    Button loginButton;
    TextView tvregister;
    //    private UserAdapter userAdapter;
    private List<User> mListUser;
    private User user;
    private TextView forgotPassword;

    private ItemRepository itemRepository = null;
    private CategoryRepository categoryRepository = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.edit_email_login);
        password = findViewById(R.id.edit_password_login);
        loginButton = findViewById(R.id.btn_Login);
        tvregister = findViewById(R.id.tvSign_up);
        forgotPassword = findViewById(R.id.tv_forgot_password);

        mListUser = new ArrayList<>();
        mListUser = ContextDatabase.getInstance(this).userDao().getAll();
        for (int i = 0; i <mListUser.size() ; i++) {
            System.out.println(mListUser.get(i));
        }

        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });

        List<User> list = ContextDatabase.getInstance(this).userDao().getAll();
        if(list == null||list.size()==0){
            getmListUser();
        }

        // add order
        List<Order> listorder = ContextDatabase.getInstance(this).orderDao().getall();

        if(listorder == null || listorder.size() == 0){
            getListOrder();
        }

        // add Item
        List<Item> listItem = ContextDatabase.getInstance(this).itemDAO().getAllWords();

        if(listItem == null || listItem.size() == 0){
            getListItem();
        }

        //add listorderdetail
        List<OrderDetail> listorderdetail = ContextDatabase.getInstance(this).orderDetailDao().getlistAll();

        if( listorderdetail== null || listorderdetail.size() == 0){
            getListOrderDetail();
        }

        //add table

        List<Table> listTable = ContextDatabase.getInstance(this).tableDao().getListTable();

        if(listTable == null || listTable.size() ==0){
            getListTable();
        }
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence stm = text.getText().toString();
        return TextUtils.isEmpty(stm);
    }

    void checkLogin() {
        boolean isValid = true;
        if (isEmpty(email)) {
            email.setError("You must enter Email to Login!");
            isValid = false;
        } else
            if (!isEmail(email)) {
                email.setError("Email Invalid. Enter valid email!");
                isValid = false;
            }


        if (isEmpty(password)) {
            password.setError("You must enter Password to Login!");
            isValid = false;
        }

        if (isValid) {
            String emailEnter = email.getText().toString();
            String passEnter = password.getText().toString();
            user = ContextDatabase.getInstance(this).userDao().checkUser(emailEnter);

            //save login
            if (user != null && user.getPassword().equals(passEnter)) {
                SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("User_Id",user.getUserId());
                editor.commit();


                if (user.getRoleName().equals("Admin")) {
                    Intent intent = new Intent(LoginActivity.this, HomeAdminActivity.class);
                    startActivity(intent);
                }else{
//                    Intent intent = new Intent(LoginActivity.this, HomeAdminActivity.class);
//
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("User", user);
//                    intent.putExtras(bundle);
//                    startActivityForResult(intent, MY_REQUEST_CODE);
                    Intent intent = new Intent(LoginActivity.this, UserHomeActivity.class);
                    startActivity(intent);
                }


            } else {
                Toast toast = Toast.makeText(this, "Email or Password is Incorrect!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    private List<Order> getListOrder(){
        List<Order> listorder = new ArrayList<>();


        listorder.add(new Order("11/10/2022", 358000, 3 , "inprogessing","Nguyen Kieu Viet Dung"));

        listorder.add(new Order("11/10/2021", 367000, 2 , "inprogessing","Nguyen Tien Khoi"));

        listorder.add(new Order("11/10/2019", 709500, 4 , "inprogessing","Thach Duc Binh"));

        listorder.add(new Order("11/10/2020", 77500, 3 , "inprogessing","Nguyen Kieu Viet Dung"));

        listorder.add(new Order("11/10/2018", 311500, 2 , "inprogessing","Nguyen Tien Khoi"));
        for (int i = 0; i < listorder.size(); i++) {
            ContextDatabase.getInstance(this).orderDao().insertAll(listorder.get(i));
        }
        return listorder;
    }

    private List<User> getmListUser(){
        List<User> list = new ArrayList<>();

        list.add(new User( "Dat", "123", "Dat@gmail.com", "Dinh Tien Dat", "0123456789", "Bac Ninh", "Admin"));
        list.add(new User( "khoi", "123", "khoi@gmail.com", "Nguyen Tien Khoi", "0985910710", "Bac Ninh", "User"));
        list.add(new User( "dung", "123", "dung@gmail.com", "Nguyen Kieu Viet Dung", "0985910720", "Bac Ninhhhh", "User"));

        list.add(new User( "binh", "123", "binh@gmail.com", "Thach Duc Binh", "0985910720", "Bac Ninh1", "User"));
        for (int i = 0; i < list.size(); i++) {

            ContextDatabase.getInstance(this).userDao().insertUser(list.get(i));

        }


        return list;
    }

    private List<OrderDetail> getListOrderDetail(){
        List<OrderDetail> listorderdetail = new ArrayList<>();

        listorderdetail.add(new OrderDetail(1, 2, 10, 10500));
        listorderdetail.add(new OrderDetail(1, 3, 11, 12500));
        listorderdetail.add(new OrderDetail(1, 4, 11, 10500));
        listorderdetail.add(new OrderDetail(2, 1, 20, 12500));
        listorderdetail.add(new OrderDetail(2, 2, 4, 10500));
        listorderdetail.add(new OrderDetail(2, 3, 6, 12500));
        listorderdetail.add(new OrderDetail(3, 3, 10, 12500));
        listorderdetail.add(new OrderDetail(3, 4, 14, 10500));
        listorderdetail.add(new OrderDetail(3, 5, 15, 12500));
        listorderdetail.add(new OrderDetail(3, 1, 20, 12500));
        listorderdetail.add(new OrderDetail(4, 3, 2, 12500));
        listorderdetail.add(new OrderDetail(4, 4, 5, 10500));
        listorderdetail.add(new OrderDetail(5, 2, 6, 10500));
        listorderdetail.add(new OrderDetail(5, 1, 4, 12500));
        listorderdetail.add(new OrderDetail(5, 3, 7, 12500));
        listorderdetail.add(new OrderDetail(5, 4, 7, 10500));
        listorderdetail.add(new OrderDetail(5, 5, 3, 12500));


        for (int i = 0; i < listorderdetail.size(); i++) {
            ContextDatabase.getInstance(this).orderDetailDao().insertOrder(listorderdetail.get(i));
        }

        return listorderdetail;
    }

    private List<Item> getListItem() {
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
            Item item3 = new Item("Com Rang",12500,12,true,2,"com","ngon");
            Item item4 = new Item("Pho Bo",10500,15,true,3,"pho","ngon");
            Item item5 = new Item("Pho Ga",12500,12,true,3,"pho","ngon");
            itemRepository.insertItem(item);
            itemRepository.insertItem(item2);
            itemRepository.insertItem(item3);
            itemRepository.insertItem(item4);
            itemRepository.insertItem(item5);
        }

        itemListItem = itemRepository.getAllItem();

        return itemListItem;
    }

    private List<Table> getListTable(){

        List<Table> listTable = new ArrayList<>();

        listTable.add(new Table(2,2,"inactive"));
        listTable.add(new Table(3,2,"inactive"));
        listTable.add(new Table(4,3,"inactive"));
        listTable.add(new Table(5,3,"inactive"));
        listTable.add(new Table(6,4,"inactive"));
        listTable.add(new Table(6,4,"active"));

        for (int i = 0; i < listTable.size(); i++) {
            ContextDatabase.getInstance(this).tableDao().insertTable(listTable.get(i));
        }

        return listTable;
    }


}
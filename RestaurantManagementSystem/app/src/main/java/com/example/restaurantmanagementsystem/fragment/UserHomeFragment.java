package com.example.restaurantmanagementsystem.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.restaurantmanagementsystem.R;

import com.example.restaurantmanagementsystem.CartActivity;
import com.example.restaurantmanagementsystem.FoodDetailActivity;
import com.example.restaurantmanagementsystem.UserHomeActivity;
import com.example.restaurantmanagementsystem.adapter.ItemListForUserAdapter;
import com.example.restaurantmanagementsystem.entity.Cart;
import com.example.restaurantmanagementsystem.entity.Category;
import com.example.restaurantmanagementsystem.entity.Item;
import com.example.restaurantmanagementsystem.respository.CartRepository;
import com.example.restaurantmanagementsystem.respository.CategoryRepository;
import com.example.restaurantmanagementsystem.respository.ItemRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserHomeFragment extends Fragment {

    private List<Item> itemList = new ArrayList<>();
    private List<Cart> cartList = new ArrayList<>();

    RecyclerView recyclerView;
    private View mView;
    private ItemRepository itemRepository = null;
    private CategoryRepository categoryRepository = null;
    private CartRepository cartRepository = null;
    private ItemListForUserAdapter itemListForUserAdapter;
    private UserHomeActivity userHomeActivity;
    private static final int ITEM_DETAIL_REQUEST = 25;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserHomeFragment newInstance(String param1, String param2) {
        UserHomeFragment fragment = new UserHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_user_home, container, false);
        userHomeActivity = (UserHomeActivity) getActivity();

        cartRepository = new CartRepository(userHomeActivity);

        itemListForUserAdapter = new ItemListForUserAdapter(new ItemListForUserAdapter.IClickItemItem() {
            @Override
            public void addCart(Item item) {
                handleAddCart(item);

            }

            @Override
            public void itemDetail(Item item) {
                handleItemDetail(item);
            }
        });

        recyclerView = mView.findViewById(R.id.recycle_view_item_for_user);
//        ItemListAdapter itemListAdapter = new ItemListAdapter(this, getItemList());
        itemListForUserAdapter.setData(new ArrayList<>());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(userHomeActivity, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(itemListForUserAdapter);
        getItemList();
        loadData();

//        ImageButton cart = mView.findViewById(R.id.btn_cart);
//        cart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(userHomeActivity, CartActivity.class);
//                startActivity(intent);
//            }
//        });

        return mView;
    }

    private void loadData(){
        userHomeActivity = (UserHomeActivity) getActivity();
        itemRepository = new ItemRepository(userHomeActivity);
        itemListForUserAdapter.setData(itemRepository.getItemForMenu());
    }

    private List getItemList(){

        userHomeActivity = (UserHomeActivity) getActivity();

        itemRepository = new ItemRepository(userHomeActivity);
        categoryRepository = new CategoryRepository(userHomeActivity);

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
        for(int i = 0; i <itemListItem.size();i++){
            itemList.add(itemListItem.get(i));
        }
        return itemList;
    }
    private void handleAddCart(Item item){
        userHomeActivity = (UserHomeActivity) getActivity();

        cartRepository = new CartRepository(userHomeActivity);

        Cart check = cartRepository.getCartByitemId(item.getItemId());

        if(check==null) {

            SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
            int userId = sharedPreferences.getInt("User_Id",0);

            Cart c = new Cart(userId,item.getItemId(), item.getItemName(), "",
                    item.getPrice(), 1, item.isStatus(), item.getCategoryId(),
                    item.getCategoryName(), item.getDescription());
            cartRepository.insertCart(c);
            Toast.makeText(userHomeActivity, "Add to cart successfully", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(userHomeActivity, "You have this item in your cart", Toast.LENGTH_SHORT).show();
        }

    }
    private void handleItemDetail(Item item){
        userHomeActivity = (UserHomeActivity) getActivity();
        Intent intent = new Intent(userHomeActivity, FoodDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_item_user",item);
        intent.putExtras(bundle);
        startActivityForResult(intent,ITEM_DETAIL_REQUEST);
    }
}
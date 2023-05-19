package com.example.restaurantmanagementsystem.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.restaurantmanagementsystem.HomeAdminActivity;
import com.example.restaurantmanagementsystem.ItemListActivity;
import com.example.restaurantmanagementsystem.OrderActivity;
import com.example.restaurantmanagementsystem.R;
import com.example.restaurantmanagementsystem.database.ContextDatabase;

public class HomeAdminFragment extends Fragment {
    ImageView ImageViewProduct, ImageViewOrder;

    private TextView order,totalPrice,product;

    private HomeAdminActivity homeAdminActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_admin_fragment,container,false);

        homeAdminActivity = (HomeAdminActivity) getActivity();

        ImageViewProduct = (ImageView) view.findViewById(R.id.btn_product);
        ImageViewOrder = (ImageView) view.findViewById(R.id.imv_order);

        order = view.findViewById(R.id.tv_admin_order);
        totalPrice = view.findViewById(R.id.tv_admin_sales);
        product = view.findViewById(R.id.tv_admin_product);

        int numberOrder = ContextDatabase.getInstance(homeAdminActivity).orderDao().countOrderInprogress();
        double total = ContextDatabase.getInstance(homeAdminActivity).orderDao().sumTotalPrice();
        int numberItem = ContextDatabase.getInstance(homeAdminActivity).itemDAO().countItemActive();

        order.setText(String.valueOf(numberOrder));
        totalPrice.setText(String.valueOf(total));
        product.setText(String.valueOf(numberItem));

        ImageViewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // code cua Binh
                Intent intent = new Intent(getActivity(), ItemListActivity.class);
                startActivity(intent);

            }
        });



        ImageViewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent( getActivity(), OrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("order", "order" );
                intent1.putExtras(bundle);

                startActivityForResult(intent1,10);
            }
        });


        return view;
    }
}

package com.example.restaurantmanagementsystem.adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.restaurantmanagementsystem.entity.User;
import com.example.restaurantmanagementsystem.R;

import java.util.List;

public class MyCustomerAdapter extends RecyclerView.Adapter<MyCustomerAdapter.MyCustomerViewHolder> {

    private Context mContext;
    private List<User> listMyCustomer ;

    public MyCustomerAdapter(Context mContext){
        this.mContext = mContext;
    }

    public void setData(List<User> list){
        this.listMyCustomer = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyCustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycustomer_item,parent,false);
        return new MyCustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCustomerViewHolder holder, int position) {
        User user = listMyCustomer.get(position);
        System.out.println(user);
        if(user == null){
            return;
        }
        holder.tvmcicustomername.setText(user.getFullName());
        holder.tvmciphone.setText(user.getMobile());
        holder.tvmciemail.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        if(listMyCustomer !=null){
            return listMyCustomer.size();
        }
        return 0;
    }


    public class MyCustomerViewHolder extends RecyclerView.ViewHolder{
        private TextView tvmcicustomername, tvmciphone,tvmciemail;

        public MyCustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvmcicustomername = itemView.findViewById(R.id.tvmci_customername);
            tvmciphone = itemView.findViewById(R.id.tvmci_phone);

            tvmciemail = itemView.findViewById(R.id.tvmci_email);

        }
    }
}

package com.example.restaurantmanagementsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.restaurantmanagementsystem.entity.OrderDetail;
import com.example.restaurantmanagementsystem.R;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.OrderDetailViewHolder> {
    private Context mContext;

    private List<OrderDetail> mListOrderDetail;

    public OrderDetailAdapter(Context mContext){
        this.mContext = mContext;
    }

    public void setData(List<OrderDetail> list){
        this.mListOrderDetail = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_detail_item, parent,false);
        return new OrderDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailViewHolder holder, int position) {
        OrderDetail orderDetail = mListOrderDetail.get(position);

        if(orderDetail == null){
            return;
        }
        holder.tvodiitemname.setText(orderDetail.getItemName());
        holder.tvodiquantity.setText(orderDetail.getQuantity()+"");
        holder.tvodiprice.setText(orderDetail.getItemPrice()+"");
    }

    @Override
    public int getItemCount() {
        if(mListOrderDetail != null){
            return mListOrderDetail.size();
        }
        return 0;
    }

    public class OrderDetailViewHolder extends RecyclerView.ViewHolder {


        private TextView tvodiitemname, tvodiquantity, tvodiprice;



        public OrderDetailViewHolder(@NonNull View itemView) {
            super(itemView);

            tvodiitemname = itemView.findViewById(R.id.tvodi_itemname);
            tvodiquantity = itemView.findViewById(R.id.tvodi_quantity);
            tvodiprice = itemView.findViewById(R.id.tvodi_price);

        }
    }
}

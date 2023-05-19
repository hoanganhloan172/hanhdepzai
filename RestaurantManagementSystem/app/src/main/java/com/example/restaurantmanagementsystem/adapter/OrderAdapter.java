package com.example.restaurantmanagementsystem.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.restaurantmanagementsystem.entity.Order;
import com.example.restaurantmanagementsystem.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    //    private Context mContext;
    private List<Order> mListOrder;


    private clickItemOrder clickItemOrder;

    public interface clickItemOrder {
        void acceptorder(Order order);

        void declineorder(Order order);

        void vieworder(Order order);
    }

    public OrderAdapter(OrderAdapter.clickItemOrder clickItemOrder) {
        this.clickItemOrder = clickItemOrder;
    }


//    public OrderAdapter(Context mContext) {
//        this.mContext = mContext;
//    }

    public void setData(List<Order> list) {
        this.mListOrder = list;
        notifyDataSetChanged();

    }




    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);

        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = mListOrder.get(position);
        if (order == null) {

            return;
        }

        holder.tvcustomerName.setText(order.getCustomerName());
        holder.tvdate.setText(order.getOrderDate());
        holder.tvprice.setText(order.getTotalPrice() + "");
        holder.tvstatus.setText(order.getStatus());


        holder.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemOrder.acceptorder(order);
            }
        });


        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                clickItemOrder.vieworder(order);

            }
        });


        holder.btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemOrder.declineorder(order);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListOrder != null) {
            return mListOrder.size();
        }
        return 0;
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {


        private TextView tvcustomerName, tvstatus, tvdate, tvprice;

        private Button btnAccept, btnDecline, btnView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            tvcustomerName = itemView.findViewById(R.id.tvcustomerName);
            tvstatus = itemView.findViewById(R.id.tvstatus);
            tvdate = itemView.findViewById(R.id.tvorderdate);
            tvprice = itemView.findViewById(R.id.tvprice);

            btnAccept = itemView.findViewById(R.id.btn_accept);
            btnDecline = itemView.findViewById(R.id.btn_decline);
            btnView = itemView.findViewById(R.id.btn_view);
        }
    }
}
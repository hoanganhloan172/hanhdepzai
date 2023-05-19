package com.example.restaurantmanagementsystem.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagementsystem.R;
import com.example.restaurantmanagementsystem.entity.Order;

import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder> {
    private List<Order> mListOrder;


    private OrderHistoryAdapter.clickItemOrder clickItemOrder;

    public interface clickItemOrder {


        void vieworder(Order order);
    }

    public OrderHistoryAdapter(OrderHistoryAdapter.clickItemOrder clickItemOrder) {
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
    public OrderHistoryAdapter.OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_history, parent, false);

        return new OrderHistoryAdapter.OrderHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryViewHolder holder, int position) {
        Order order = mListOrder.get(position);
        if (order == null) {

            return;
        }

        holder.tvcustomerName.setText(order.getCustomerName());
        holder.tvdate.setText(order.getOrderDate());
        holder.tvprice.setText(order.getTotalPrice() + "");
        holder.tvstatus.setText(order.getStatus());



        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                clickItemOrder.vieworder(order);

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

    public class OrderHistoryViewHolder extends RecyclerView.ViewHolder {


        private TextView tvcustomerName, tvstatus, tvdate, tvprice;

        private Button btnView;

        public OrderHistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvcustomerName = itemView.findViewById(R.id.tvcustomerNameOrderHistory);
            tvstatus = itemView.findViewById(R.id.tvstatusOrderHistory);
            tvdate = itemView.findViewById(R.id.tvorderdateOrderHistory);
            tvprice = itemView.findViewById(R.id.tvpriceOrderHistory);

            btnView = itemView.findViewById(R.id.btn_view_OrderHistory);
           ;
        }
    }
}

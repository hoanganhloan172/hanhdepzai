package com.example.restaurantmanagementsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagementsystem.R;
import com.example.restaurantmanagementsystem.entity.Item;

import java.util.List;

public class ItemListForUserAdapter extends RecyclerView.Adapter<ItemListForUserAdapter.ItemListViewHolder> {
    private Context context;
    private List<Item> itemList;

    private IClickItemItem iClickItemItem;

    public interface IClickItemItem{
        void addCart(Item item);
        void itemDetail(Item item);
    }

    public ItemListForUserAdapter(IClickItemItem iClickItemItem) {
        this.iClickItemItem = iClickItemItem;
    }


    public void setData(List<Item> item){
        this.itemList = item;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Load layout for one word item
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_item_for_user,parent,false);
        return new ItemListViewHolder(viewItem);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemListViewHolder holder, int position) {
        // Get data from wordlist
        Item item = itemList.get(position);
        if(item==null){
            return;
        }
        //set data to one item
        holder.itemName.setText(item.getItemName());
//        holder.itemCategory.setText(item.getCategoryName());
        holder.itemPrice.setText(Double.toString(item.getPrice()));
        holder.image.setImageResource(R.drawable.bunbo);
        holder.itemAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemItem.addCart(item);
            }
        });
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemItem.itemDetail(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    protected class ItemListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView itemName,itemCategory,itemPrice;
        private ImageView image;
        private ImageButton itemAddCart;

            public ItemListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemName = itemView.findViewById(R.id.item_for_user_name);
//            this.itemCategory = itemView.findViewById(R.id.item_for_user_category);
            this.itemPrice = itemView.findViewById(R.id.item_for_user_price);
            this.image = itemView.findViewById(R.id.image_item_for_user);
            this.itemAddCart = itemView.findViewById(R.id.btn_add_cart);

        }

        @Override
        public void onClick(View v){
        }
    }



}

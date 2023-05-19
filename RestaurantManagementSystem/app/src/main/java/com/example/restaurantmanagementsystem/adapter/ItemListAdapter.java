package com.example.restaurantmanagementsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagementsystem.R;
import com.example.restaurantmanagementsystem.entity.Item;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemListViewHolder> {
    private Context context;
    private List<Item> itemList;

    private IClickItemItem iClickItemItem;

    public interface IClickItemItem{
        void updateItem(Item item);
        void changeStatusItem(Item item);
    }

    public ItemListAdapter(IClickItemItem iClickItemItem) {
        this.iClickItemItem = iClickItemItem;
    }

//    public ItemListAdapter(Context context, List<Item> itemList){
//        this.context = context;
//        this.itemList = itemList;
//    }

    public void setData(List<Item> item){
        this.itemList = item;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Load layout for one word item
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_item,parent,false);
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
        holder.itemCategory.setText(item.getCategoryName());
        holder.itemPrice.setText(Double.toString(item.getPrice()));
        holder.itemStatus.setChecked(item.isStatus());
        holder.image.setImageResource(R.drawable.bunbo);
        holder.itemIconEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemItem.updateItem(item);
            }
        });
        holder.itemStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                iClickItemItem.changeStatusItem(item);
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
        private ItemListAdapter itemListAdapter;
        private ImageButton itemIconEdit;
        private Switch itemStatus;
//        public ItemListViewHolder(@NonNull View itemView,ItemListAdapter adapter) {
            public ItemListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemName = itemView.findViewById(R.id.item_name);
            this.itemCategory = itemView.findViewById(R.id.item_category);
            this.itemPrice = itemView.findViewById(R.id.item_price);
            this.image = itemView.findViewById(R.id.image_item);
            this.itemIconEdit = itemView.findViewById(R.id.item_icon_edit);
            this.itemStatus = itemView.findViewById(R.id.item_status);


//            this.itemListAdapter = adapter;
//            textViewItem.setOnClickListener(this);
        }
//        public ItemListViewHolder(@NonNull View itemView){
//            super(itemView);
//        }

        @Override
        public void onClick(View v){
//            textViewItem.setText("Clicked?" + textViewItem.getText());
        }
    }



}

package com.example.restaurantmanagementsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagementsystem.R;
import com.example.restaurantmanagementsystem.entity.Item;
import com.example.restaurantmanagementsystem.entity.MenuItem;

import java.util.List;

public class ItemInMenuAdapter extends RecyclerView.Adapter<ItemInMenuAdapter.ItemInMenuViewHolder> {
    private Context context;
    private List<Item> itemList;
    //
    private List<MenuItem> itemListChoice;

    private IClickItemInMenu iClickItemInMenu;

    public interface IClickItemInMenu{
        void choiceItem(Item item);
        void notChoiceItem(Item item);

    }

    public ItemInMenuAdapter(IClickItemInMenu iClickItemInMenu) {
        this.iClickItemInMenu = iClickItemInMenu;
    }

//        public ItemInMenuAdapter(Context context){
//        this.context = context;
//    }
    public void setData(List<Item> item,List<MenuItem> itemChoice){
        this.itemList = item;
        //
        this.itemListChoice = itemChoice;
        //
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemInMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Load layout for one word item
        View viewItem = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_list_item_on_menu,parent,false);
        return new ItemInMenuViewHolder(viewItem);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemInMenuViewHolder holder, int position) {
        // Get data from wordlist
        Item item = itemList.get(position);
        if(item==null){
            return;
        }
        for (int i = 0; i < itemListChoice.size(); i++) {
            if (item.getItemId()==itemListChoice.get(i).getItemId()){
                holder.itemNameInMenu.setChecked(true);
            }
        }
        //set data to one item
        holder.itemNameInMenu.setText(item.getItemName());
        holder.itemPriceInMenu.setText(String.valueOf(item.getPrice()));
        holder.itemNameInMenu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()) {
                    iClickItemInMenu.choiceItem(item);
                }else{
                    iClickItemInMenu.notChoiceItem(item);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    protected class ItemInMenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView itemPriceInMenu;
        private CheckBox itemNameInMenu;
        private ItemInMenuAdapter itemInMenuAdapter;

        public ItemInMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemNameInMenu = itemView.findViewById(R.id.item_name_on_menu);
            this.itemPriceInMenu = itemView.findViewById(R.id.item_price_on_menu);

        }


        @Override
        public void onClick(View v){

        }
    }



}

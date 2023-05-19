package com.example.restaurantmanagementsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagementsystem.R;
import com.example.restaurantmanagementsystem.entity.Menu;

import java.util.List;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.MenuListViewHolder> {
    private Context context;
    private List<Menu> menuList;

    private IClickMenu iClickMenu;

    public interface IClickMenu{
        void updateMenu(Menu menu);
        void changeStatusMenu(Menu menu);
    }

    public MenuListAdapter(IClickMenu iClickMenu) {
        this.iClickMenu = iClickMenu;
    }


    public void setData(List<Menu> menus){
        this.menuList = menus;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MenuListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Load layout for one word item
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_menu,parent,false);
        return new MenuListViewHolder(viewItem);

//        LayoutInflater inflater = LayoutInflater.from(context);
//        View heroView = inflater.inflate(R.layout.item_list_item, parent, false);
//        ItemListViewHolder viewHolder = new ItemListViewHolder(heroView);
//        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuListViewHolder holder, int position) {
        // Get data from wordlist
        Menu menu = menuList.get(position);
        if(menu==null){
            return;
        }
        //set data to one item
        holder.menuName.setText(menu.getMenuName());
        holder.menuStatus.setChecked(menu.isMenuStatus());
        holder.menuIconEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickMenu.updateMenu(menu);
            }
        });
        holder.menuStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                iClickMenu.changeStatusMenu(menu);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    protected class MenuListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView menuName;
        private MenuListAdapter menuListAdapter;
        private ImageButton menuIconEdit;
        private Switch menuStatus;
        public MenuListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.menuName = itemView.findViewById(R.id.menu_name);
            this.menuIconEdit = itemView.findViewById(R.id.menu_icon_edit);
            this.menuStatus = itemView.findViewById(R.id.menu_status);


        }

        @Override
        public void onClick(View v){
//            textViewItem.setText("Clicked?" + textViewItem.getText());
        }
    }



}

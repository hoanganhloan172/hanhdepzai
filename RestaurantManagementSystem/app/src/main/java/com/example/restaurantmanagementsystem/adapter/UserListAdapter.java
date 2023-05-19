package com.example.restaurantmanagementsystem.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagementsystem.entity.User;
import com.example.restaurantmanagementsystem.R;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends  RecyclerView.Adapter<UserListAdapter.UserListViewHolder> {


    private clickUserItem clickUserItem;
    private List<User> listUser = new ArrayList<>();

    public interface clickUserItem{
        void viewInfo(User user);
    }

    public UserListAdapter(UserListAdapter.clickUserItem clickUserItem) {
        this.clickUserItem = clickUserItem;
    }

    public void setData(List<User> list){
        this.listUser = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_list,parent,false);

        return new UserListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListViewHolder holder, int position) {
        User user = listUser.get(position);

        holder.tvusername.setText(user.getFullName());
        holder.tvemail.setText(user.getEmail());
        holder.tvphone.setText(user.getMobile());
        holder.viewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickUserItem.viewInfo(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class UserListViewHolder extends RecyclerView.ViewHolder{
        private TextView tvusername, tvphone, tvemail;
        private ImageView viewUser;
        public UserListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvusername = itemView.findViewById(R.id.tv_itemuserlist_user_name);
            tvphone = itemView.findViewById(R.id.tv_itemuserlist_phone);

            tvemail = itemView.findViewById(R.id.tv_itemuserlist_mail);

            viewUser = itemView.findViewById(R.id.imv_itemuserlist_viewinfo);

        }
    }
}

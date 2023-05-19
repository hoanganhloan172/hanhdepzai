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
import com.example.restaurantmanagementsystem.entity.Cart;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{

    private Context context;
    private List<Cart> cartList;

    private IClickCart iClickCart;

    public interface IClickCart{
        void plus(Cart cart);
        void minus(Cart cart);
        void delete(Cart cart);
    }

    public CartAdapter(IClickCart iClickCart) {
        this.iClickCart = iClickCart;
    }

    public void setData(List<Cart> carts){
        this.cartList = carts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_cart,parent,false);
        return new CartViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = cartList.get(position);
        if(cart==null){
            return;
        }
        //set data to one item
        holder.cartItemName.setText(cart.getItemName());
        holder.cartItemCategory.setText(cart.getCategoryName());
        holder.cartItemPrice.setText(Double.toString(cart.getPrice()));
        holder.cartItemQuantity.setText(Integer.toString(cart.getQuantity()));
        holder.image.setImageResource(R.drawable.bunbo);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickCart.delete(cart);
            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickCart.plus(cart);
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickCart.minus(cart);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    protected class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView cartItemName, cartItemCategory, cartItemPrice,cartItemQuantity;
        private ImageView image;
        private ImageButton delete,plus,minus;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cartItemName = itemView.findViewById(R.id.cart_item_name);
            this.cartItemCategory = itemView.findViewById(R.id.cart_item_category);
            this.cartItemPrice = itemView.findViewById(R.id.cart_item_price);
            this.cartItemQuantity = itemView.findViewById(R.id.quantity_in_cart);
            this.image = itemView.findViewById(R.id.image_cart_item);
            this.delete = itemView.findViewById(R.id.btn_delete_item_in_cart);
            this.plus = itemView.findViewById(R.id.btn_plus_cart);
            this.minus = itemView.findViewById(R.id.btn_minus_cart);

        }

        @Override
        public void onClick(View v) {
        }
    }
}

package com.example.restaurantmanagementsystem.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagementsystem.entity.Table;
import com.example.restaurantmanagementsystem.R;

import java.util.ArrayList;
import java.util.List;

public class ArrangeTableAdapter extends RecyclerView.Adapter<ArrangeTableAdapter.ArrangeTableViewHolder> {

    List<Table> listTable = new ArrayList<>();
//     private Context context;

    private clickItemArrangeTable clickItemArrangeTable;
    public interface clickItemArrangeTable{

        void rdbArrange(Table table);

    }

    public ArrangeTableAdapter(ArrangeTableAdapter.clickItemArrangeTable clickItemArrangeTable) {
        this.clickItemArrangeTable = clickItemArrangeTable;
    }

//    public ArrangeTableAdapter(Context context) {
//        this.context = context;
//    }

    public void setData(List<Table> list){
        this.listTable = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArrangeTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_arrange_table,parent,false);

        return new ArrangeTableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArrangeTableViewHolder holder, int position) {
        Table table = listTable.get(position);
        if (table == null) {
            return;
        }

        holder.tableId.setText(Integer.toString(table.getId()));
        holder.tableFloorValue.setText(Integer.toString(table.getFloor()));
        holder.tableNoPerson.setText(Integer.toString(table.getNumberPerson()));

        holder.radioButtonChooseTable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    clickItemArrangeTable.rdbArrange(table);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTable.size();
    }

    public class ArrangeTableViewHolder extends RecyclerView.ViewHolder{

        private TextView tableId, tableFloorValue, tableNoPerson;
        private Switch switchStatusTable;
        private RadioButton radioButtonChooseTable;
        public ArrangeTableViewHolder(@NonNull View itemView) {
            super(itemView);
            tableId = itemView.findViewById(R.id.tv_table_id);
            tableFloorValue = itemView.findViewById(R.id.tv_table_floor_value);

            tableNoPerson = itemView.findViewById(R.id.tv_table_no_of_person);

            switchStatusTable = itemView.findViewById(R.id.switch_status_table);

            radioButtonChooseTable = itemView.findViewById(R.id.radioButtonChooseTable);



        }
    }
}

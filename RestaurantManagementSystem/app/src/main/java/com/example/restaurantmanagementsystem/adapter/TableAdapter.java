package com.example.restaurantmanagementsystem.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagementsystem.entity.Table;
import com.example.restaurantmanagementsystem.R;


import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableViewHolder>{

    private List<Table> tableList;
    private ClickItemsTable clickItemsTable;

    public interface ClickItemsTable{
        void updateTable(Table table);

        void switchtable(Table table);
    }

    public TableAdapter() {
    }

    public TableAdapter(ClickItemsTable clickItemsTable){
        this.clickItemsTable = clickItemsTable;
    }

    public void setDataTable(List<Table> tableList){
        this.tableList = tableList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table, parent, false);
        return new TableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableViewHolder holder, int position) {
        final Table table = tableList.get(position);
        if (table == null) return;

        holder.tvTableId.setText(Integer.toString(table.getId()));
        holder.tvFloor.setText(Integer.toString(table.getFloor()));
        holder.tvTableNoOfPerson.setText(Integer.toString(table.getNumberPerson()));
        if(table.getStatus().equals("active")){
            holder.switchtable.setChecked(true);
        }else{
            holder.switchtable.setChecked(false);

        }
        holder.ivEditTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemsTable.updateTable(table);
            }
        });
        holder.switchtable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemsTable.switchtable(table);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(tableList != null){
            return tableList.size();
        }
        return 0;
    }

    public class TableViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTableId, tvTableNoOfPerson, tvFloor;
        private ImageView ivEditTable, ivDeleteTable;
        private Switch switchtable;

        public TableViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTableId = itemView.findViewById(R.id.tv_table_id);
            tvTableNoOfPerson = itemView.findViewById(R.id.tv_table_no_of_person);
            ivEditTable = itemView.findViewById(R.id.iv_edit_table);
            tvFloor = itemView.findViewById(R.id.tv_table_floor_value);
            switchtable = itemView.findViewById(R.id.switch_status_table);

        }
    }
}

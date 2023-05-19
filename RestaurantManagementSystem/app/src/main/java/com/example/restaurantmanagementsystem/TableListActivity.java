package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.restaurantmanagementsystem.adapter.TableAdapter;
import com.example.restaurantmanagementsystem.database.ContextDatabase;
import com.example.restaurantmanagementsystem.entity.Table;


import java.util.ArrayList;
import java.util.List;

public class TableListActivity extends AppCompatActivity {

    private static final int MY_REQUEST_TABLE = 10;
    private TableAdapter tableAdapter;
    private RecyclerView recyclerViewTable;
    private Button btnAddTable;
    private ImageView ivEditTable, ivDeleteTable;
    private List<Table> tableList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_list);

        initUI();

        tableAdapter = new TableAdapter(new TableAdapter.ClickItemsTable() {
            @Override
            public void updateTable(Table table) {
                clickUpdateTable(table);
            }

            @Override
            public void switchtable(Table table) {
                clickswitchtable(table);
            }


        });

        tableList = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewTable.setLayoutManager(linearLayoutManager);

        loadData();
        recyclerViewTable.setAdapter(tableAdapter);

        btnAddTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TableListActivity.this, AddTableActivity.class);
                startActivity(intent);
            }
        });

    }

    private void clickswitchtable(Table table) {
        if(table.getStatus().equals("active")){
            table.setStatus("inactive");

        }else{
            table.setStatus("active");
        }

        ContextDatabase.getInstance(this).tableDao().updateTable(table);
        loadData();
    }


    private void clickUpdateTable(Table table) {
        Intent i = new Intent(TableListActivity.this, EditTableActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_table", table);
        i.putExtras(bundle);
        startActivityForResult(i, MY_REQUEST_TABLE);
    }

    private void initUI() {
        btnAddTable = findViewById(R.id.add_table);
        recyclerViewTable = findViewById(R.id.rcv_table_list);

        ivEditTable = findViewById(R.id.iv_edit_table);

    }
    private void loadData(){
        tableList = ContextDatabase.getInstance(this).tableDao().getListTable();
        tableAdapter.setDataTable(tableList);
        for (int i = 0; i < tableList.size(); i++) {
            System.out.println(tableList.get(i));
        }
    }
}
package com.example.restaurantmanagementsystem.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagementsystem.entity.Employee;
import com.example.restaurantmanagementsystem.R;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<Employee> employeeList;
    private ClickItemsEmployee clickItemsEmployee;


    public interface ClickItemsEmployee {
        void updateEmployee(Employee employee);

        void changeStatusEmployee(Employee employee);
    }


    public EmployeeAdapter() {
    }

    public EmployeeAdapter(ClickItemsEmployee clickItemsEmployee) {
        this.clickItemsEmployee = clickItemsEmployee;
    }

    public void setDataEmployee(List<Employee> employeeList) {
        this.employeeList = employeeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        final Employee employee = employeeList.get(position);
        if (employee == null) {
            return;
        }

        holder.tvEmployeeName.setText(employee.getName());
        holder.tvEmployeeDob.setText(employee.getDob());
        holder.ivImangeEm.setImageResource(R.drawable.a);
        if (employee.getStatus().equals("active")) {
            holder.switchChangeStatusEmployee.setChecked(true);
        } else {
            holder.switchChangeStatusEmployee.setChecked(false);

        }
        holder.ivEditEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemsEmployee.updateEmployee(employee);
            }
        });

        holder.switchChangeStatusEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemsEmployee.changeStatusEmployee(employee);
            }
        });


    }


    @Override
    public int getItemCount() {
        if (employeeList != null) {
            return employeeList.size();
        }

        return 0;
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {

        private TextView tvEmployeeName, tvEmployeeDob;
        private ImageView ivEditEmployee, ivDeleteEmployee,ivImangeEm;
        private Switch switchChangeStatusEmployee;


        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmployeeName = itemView.findViewById(R.id.tv_employee_name);
            tvEmployeeDob = itemView.findViewById(R.id.tv_employee_dob);
            ivEditEmployee = itemView.findViewById(R.id.iv_edit_employee);
            ivImangeEm = itemView.findViewById(R.id.iv_employee_image);
            switchChangeStatusEmployee = itemView.findViewById(R.id.switch_status_employee);

        }

    }


}

package com.example.kareem.fci_scu_project.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.classes.StudentRule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arthonsystechnologiesllp on 10/03/17.
 */

public class SelectStudentAdapter extends RecyclerView.Adapter<SelectStudentAdapter.MyViewHolder> implements Filterable {

    Activity activity;
    List<StudentRule> exampleList;
    LayoutInflater inflater;
    List<StudentRule> exampleListFull;


    //short to create constructer using command+n for mac & Alt+Insert for window

    public SelectStudentAdapter(Activity activity, List<StudentRule> exampleList) {
        this.activity = activity;
        this.exampleList = exampleList;
        inflater = activity.getLayoutInflater();
        exampleListFull = new ArrayList<>(exampleList);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_student_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        final StudentRule model = exampleList.get(position);
        holder.tvUserName.setText(model.getUserName());
        holder.checkBox.setChecked(model.isSelected());


        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.isSelected()) {
                    model.setSelected(false);
                } else {

                    model.setSelected(true);
                }
                exampleList.set(position, model);

                updateRecords(exampleList);
            }
        });


    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }


    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    public Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<StudentRule> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (StudentRule item : exampleListFull) {
                    if (item.getUserName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            exampleList.clear();
            exampleList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public void updateRecords(List<StudentRule> exampleList) {
        this.exampleList = exampleList;

        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvUserName;
        CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvUserName = itemView.findViewById(R.id.tv_user_name);
            checkBox = itemView.findViewById(R.id.check_box);
        }
    }


}
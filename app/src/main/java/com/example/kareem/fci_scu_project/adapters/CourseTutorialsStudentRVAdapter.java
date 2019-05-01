package com.example.kareem.fci_scu_project.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.classes.Material;
import com.example.kareem.fci_scu_project.classes.Tutorial;

import java.util.List;

public class CourseTutorialsStudentRVAdapter extends RecyclerView.Adapter<CourseTutorialsStudentRVAdapter.MyViewHolder> {

    private Context context;
    private List<Material> materialList;

    public CourseTutorialsStudentRVAdapter(Context context, List<Material> materialList) {
        this.context = context;
        this.materialList = materialList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.course_tutorials_student_layout,parent,false);

        return new CourseTutorialsStudentRVAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tutorialName.setText(materialList.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return materialList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tutorialName;

        public MyViewHolder(View itemView) {
            super(itemView);
            tutorialName = itemView.findViewById(R.id.course_tutorials_stud_tutorialName);

        }
    }
}

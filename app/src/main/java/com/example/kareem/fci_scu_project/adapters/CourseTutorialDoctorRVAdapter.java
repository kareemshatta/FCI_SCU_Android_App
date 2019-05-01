package com.example.kareem.fci_scu_project.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.classes.Material;
import com.example.kareem.fci_scu_project.classes.Tutorial;

import java.util.List;

public class CourseTutorialDoctorRVAdapter extends RecyclerView.Adapter<CourseTutorialDoctorRVAdapter.MyViewHolder>{

    private Context context;
    public List<Material> materialList;

    public CourseTutorialDoctorRVAdapter(Context context, List<Material> materialList) {
        this.context = context;
        this.materialList = materialList;
    }

    @Override
    public CourseTutorialDoctorRVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.course_tutorial_doctor_layout,parent,false);

        return new CourseTutorialDoctorRVAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tutorialName.setText(materialList.get(position).getName());
        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                materialList.remove(position);
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return materialList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tutorialName;
        ImageButton deleteItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            tutorialName = itemView.findViewById(R.id.course_tutorials_doc_tutorialName);
            deleteItem = itemView.findViewById(R.id.course_tutorials_doc_imageButton);
        }

    }
}

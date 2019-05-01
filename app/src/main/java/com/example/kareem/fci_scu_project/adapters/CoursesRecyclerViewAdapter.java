package com.example.kareem.fci_scu_project.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.activities.CourseDetailsActivity;
import com.example.kareem.fci_scu_project.activities.HomeActivity;
import com.example.kareem.fci_scu_project.classes.Subject;

import java.util.List;

import static com.example.kareem.fci_scu_project.Helpers.Constants.COURSE_NAME;
import static com.example.kareem.fci_scu_project.Helpers.Constants.SUBJECT_ID;

public class CoursesRecyclerViewAdapter extends RecyclerView.Adapter<CoursesRecyclerViewAdapter.MyViewHolder>{

    private Context context;
    private List<Subject> subjectList;

    public CoursesRecyclerViewAdapter(Context context, List<Subject> subjectList) {
        this.context = context;
        this.subjectList = subjectList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.course_cardview_layout,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.courseNameTV.setText(subjectList.get(i).getName());
        myViewHolder.courseCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CourseDetailsActivity.class);
//                intent.putExtra("course_name",subjectList.get(i).getName());
                context.startActivity(intent);
                SUBJECT_ID = subjectList.get(i).getSubjectId();
                COURSE_NAME = subjectList.get(i).getName();

            }
        });

    }


    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView courseNameTV;
        CardView courseCardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            courseNameTV = itemView.findViewById(R.id.course_name);
            courseCardView = (CardView)itemView.findViewById(R.id.course_card_item);


        }



    }
}

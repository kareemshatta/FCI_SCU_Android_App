package com.example.kareem.fci_scu_project.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.classes.Quiz;
import com.example.kareem.fci_scu_project.classes.Task;

import java.util.List;

public class CourseQuizDoctorRVAdapter extends RecyclerView.Adapter<CourseQuizDoctorRVAdapter.MyViewHolder>{

    private Context context;
    public List<Task> taskList;

    public CourseQuizDoctorRVAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @Override
    public CourseQuizDoctorRVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.course_quiz_doctor_layout,parent,false);

        return new CourseQuizDoctorRVAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.quizName.setText(taskList.get(position).getTaskName());
        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                taskList.remove(position);
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView quizName;
        ImageButton deleteItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            quizName = itemView.findViewById(R.id.course_quiz_doc_quizName);
            deleteItem = itemView.findViewById(R.id.course_quiz_doc_imageButton);
        }

    }
}

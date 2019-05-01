package com.example.kareem.fci_scu_project.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kareem.fci_scu_project.Helpers.Constants;
import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.activities.CourseQuizDetailsStudentActivity;
import com.example.kareem.fci_scu_project.classes.Quiz;
import com.example.kareem.fci_scu_project.classes.Task;

import java.util.List;

import static com.example.kareem.fci_scu_project.Helpers.Constants.TASK_DATA;

public class CourseQuizStudRVAdapter extends RecyclerView.Adapter<CourseQuizStudRVAdapter.MyViewHolder>{

    private Context context;
    public List<Task> taskList;

    public CourseQuizStudRVAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @Override
    public CourseQuizStudRVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.course_quiz_stud_layout,parent,false);

        return new CourseQuizStudRVAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CourseQuizStudRVAdapter.MyViewHolder holder, final int position) {
        holder.quizName.setText(taskList.get(position).getTaskName());
        holder.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TASK_DATA = taskList.get(position);
                Intent intent = new Intent(context, CourseQuizDetailsStudentActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView quizName;
        ImageButton nextBtn;

        public MyViewHolder(View itemView) {
            super(itemView);
            quizName = itemView.findViewById(R.id.course_quiz_stud_quizName);
            nextBtn = itemView.findViewById(R.id.course_quiz_stud_imageButton);
        }

    }




}

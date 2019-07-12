package com.example.kareem.fci_scu_project.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.Retrofit.ApiInterface;
import com.example.kareem.fci_scu_project.Retrofit.RetrofitClient;
import com.example.kareem.fci_scu_project.classes.Quiz;
import com.example.kareem.fci_scu_project.classes.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.kareem.fci_scu_project.Helpers.Constants.SUBJECT_ID;

public class CourseQuizDoctorRVAdapter extends RecyclerView.Adapter<CourseQuizDoctorRVAdapter.MyViewHolder>{

    private Context context;
    public List<Task> taskList;
    private ProgressBar progressBar;

    public CourseQuizDoctorRVAdapter(Context context, List<Task> taskList,ProgressBar progressBar) {
        this.context = context;
        this.taskList = taskList;
        this.progressBar = progressBar;
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

                progressBar.setVisibility(View.VISIBLE);

                ApiInterface getResponse = RetrofitClient.getClient().create(ApiInterface.class);
                Call<String> call = getResponse.deleteTaskCall(taskList.get(position).getTaskId());

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        if (response.isSuccessful()){

                            progressBar.setVisibility(View.INVISIBLE);

                            String message = response.body();

                            taskList.remove(position);
                            notifyDataSetChanged();

                            Toast.makeText(context, "Delete task is "+message, Toast.LENGTH_SHORT).show();


                        }else {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(context, "Task deleting unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.v("Response gotten is", t.getMessage());
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(context, "problem deleting task " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                });

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

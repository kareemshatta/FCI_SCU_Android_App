package com.example.kareem.fci_scu_project.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kareem.fci_scu_project.Retrofit.ApiInterface;
import com.example.kareem.fci_scu_project.Retrofit.RetrofitClient;
import com.example.kareem.fci_scu_project.adapters.CourseQuizStudRVAdapter;
import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.classes.Task;
import com.example.kareem.fci_scu_project.classes.TasksResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.kareem.fci_scu_project.Helpers.Constants.SUBJECT_ID;

public class CourseQuizStudentActivity extends AppCompatActivity {


    private List<Task> taskList;
    private RecyclerView quizRecyclerView;
    private CourseQuizStudRVAdapter rvAdapter;
    private TasksResponse tasksResponse;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_quiz_student);

        getSupportActionBar().setTitle(R.string.course_details_quizes_btn_txt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.quiz_stud_progressBar);
        progressBar.setVisibility(View.VISIBLE);
        getTasks();

    }
    private void getTasks(){

        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);

        Call<TasksResponse> call = apiInterface.getTasksCall(SUBJECT_ID);
        call.enqueue(new Callback<TasksResponse>() {
            @Override
            public void onResponse(Call<TasksResponse> call, Response<TasksResponse> response) {
                tasksResponse = response.body();
                boolean status = tasksResponse.getStatus();

                if (status) {

                    taskList = tasksResponse.getTasks();

                    quizRecyclerView = findViewById(R.id.course_quiz_stud_recyclerView);
                    rvAdapter = new CourseQuizStudRVAdapter(getBaseContext(),taskList);
                    quizRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    quizRecyclerView.setAdapter(rvAdapter);

//                        Toast.makeText(getActivity().getBaseContext(), ""+subjectList.size(), Toast.LENGTH_SHORT).show();

                    progressBar.setVisibility(View.INVISIBLE);

                } else {
                    String message = response.message();
                    Toast.makeText(getBaseContext(), "Error : " + message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<TasksResponse> call, Throwable t) {
                Log.i("Error", "onFailure: "+t.getMessage());
            }
        });
    }


}

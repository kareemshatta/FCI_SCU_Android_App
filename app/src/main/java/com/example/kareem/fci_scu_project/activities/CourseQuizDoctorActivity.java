package com.example.kareem.fci_scu_project.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kareem.fci_scu_project.Retrofit.ApiInterface;
import com.example.kareem.fci_scu_project.Retrofit.RetrofitClient;
import com.example.kareem.fci_scu_project.adapters.CourseQuizDoctorRVAdapter;
import com.example.kareem.fci_scu_project.adapters.CourseQuizStudRVAdapter;
import com.example.kareem.fci_scu_project.classes.Quiz;
import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.classes.Task;
import com.example.kareem.fci_scu_project.classes.TasksResponse;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.kareem.fci_scu_project.Helpers.Constants.SUBJECT_ID;

public class CourseQuizDoctorActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 11;
    private List<Task> taskList;
    private RecyclerView quizRecyclerView;
    private CourseQuizDoctorRVAdapter rvAdapter;
    private FloatingActionButton fab;
    private TextView quizName;
    private TextView quizDeadline;
    private String quzName , quzDeadline;
    private DatePickerDialog dateDilog;
    private Button dateBtn;
    private TasksResponse tasksResponse;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_quiz_doctor);

        getSupportActionBar().setTitle("Quizes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        fab = findViewById(R.id.course_quiz_doc_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddQuizDialog();
            }
        });

        progressBar = findViewById(R.id.quiz_doc_progressBar);
        progressBar.setVisibility(View.VISIBLE);

        getTasks();
        initDialogs();

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

                    quizRecyclerView = findViewById(R.id.course_quiz_doc_recyclerview);
                    rvAdapter = new CourseQuizDoctorRVAdapter(getBaseContext(),taskList);
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
    public void showAddQuizDialog() {

        final Dialog dialog = new Dialog(this);
        Button selectButton;
        Button addButton;

        dialog.setContentView(R.layout.course_add_quiz_layout);
        Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null) {
            dialog.getWindow().getAttributes().width = getResources().getDisplayMetrics().widthPixels;
        }
        dialog.show();

        selectButton = dialog.findViewById(R.id.course_add_quiz_selectbtn);
        addButton = dialog.findViewById(R.id.course_add_quiz_addbtn);
        quizName = dialog.findViewById(R.id.course_add_quiz_name);

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileChooserAction();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //adding tutorial here.
                Task task = new Task();
                task.setTaskName(quzName);
                taskList.add(task);
                rvAdapter.notifyDataSetChanged();
                dialog.hide();
            }
        });

        dateBtn = dialog.findViewById(R.id.course_add_quiz_deadlineBtn);
        quizDeadline = dialog.findViewById(R.id.course_add_quiz_deadline);

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateDilog.show(getFragmentManager(),"datePickerDialog");
            }
        });

    }
    public void fileChooserAction(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("application/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,REQUEST_CODE);
    }
    void initDialogs() {
        Calendar now = Calendar.getInstance();
        dateDilog = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        quzDeadline = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
                        quizDeadline.setText(quzDeadline);
                    }
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dateDilog.setAccentColor(getResources().getColor(R.color.colorAccent));

        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.DATE, 1);
        dateDilog.setMinDate(gc);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){

            if (data != null){
                Uri uri = data.getData();
                quzName = uri.getPath().substring(uri.getPath().lastIndexOf("/")+1);
                quizName.setText(quzName);


            }
        }
    }
}

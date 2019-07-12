package com.example.kareem.fci_scu_project.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.OpenableColumns;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
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
    private ProgressBar progressBar,addQuizProgressBar;
    private String filePath;
    private Dialog dialog;
    private RadioGroup radioGroup;
    private String provider;


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
                    rvAdapter = new CourseQuizDoctorRVAdapter(getBaseContext(),taskList,progressBar);
                    quizRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    quizRecyclerView.setAdapter(rvAdapter);
//                        Toast.makeText(getActivity().getBaseContext(), ""+subjectList.size(), Toast.LENGTH_SHORT).show();

                    progressBar.setVisibility(View.INVISIBLE);
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    String message = response.message();
                    Toast.makeText(getBaseContext(), "Error : " + message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<TasksResponse> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.i("Error", "onFailure: "+t.getMessage());
            }
        });
    }
    public void showAddQuizDialog() {

        dialog = new Dialog(this);
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
        radioGroup = dialog.findViewById(R.id.course_add_quiz_radioGroup);
        addQuizProgressBar = dialog.findViewById(R.id.add_quiz_progressBar);

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                fileChooserAction();
                launchPicker();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //adding task here.
                Task task = new Task();
                task.setTaskName(quzName);
                taskList.add(task);
                rvAdapter.notifyDataSetChanged();
                uploadFile();
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

    void initDialogs() {
        Calendar now = Calendar.getInstance();
        dateDilog = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        quzDeadline = (monthOfYear+1)+"/"+dayOfMonth+"/"+year;
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

//    public void fileChooserAction(){
//        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//        intent.setType("application/*");
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        startActivityForResult(intent,REQUEST_CODE);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
//
//            if (data != null){
//                Uri uri = data.getData();
//                quzName = uri.getPath().substring(uri.getPath().lastIndexOf("/")+1);
//                quizName.setText(quzName);
//
//
//            }
//        }
//    }

    ///////////////////////////////////////////////////////////////////////


    private void launchPicker() {
        new MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(REQUEST_CODE)
                .withHiddenFiles(true)
                //.withFilter(Pattern.compile(".*\\.pdf$"))
                .withTitle("Select Task file")
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String path = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            File file = new File(path);

            if (path != null) {
                Log.d("Path: ", path);
                filePath = path;
                Uri uri = Uri.fromFile(new File(file.getAbsolutePath()));
                quzName = getFileName(uri);
                quizName.setText(quzName);
                //Toast.makeText(this, "Picked file: " + path, Toast.LENGTH_LONG).show();
            }
        }

    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getLastPathSegment();
        }
        return result;
    }

    private void uploadFile() {
        if (filePath == null) {
            Toast.makeText(this, "please select an file ", Toast.LENGTH_LONG).show();
            return;
        } else {

            addQuizProgressBar.setVisibility(View.VISIBLE);

            File file = new File(filePath);
            // Parsing any Media type file

            RequestBody requestBody = RequestBody.create(MediaType.parse("application/*"), file);

            //get Time

            SimpleDateFormat df = new SimpleDateFormat("h:mm a");
            String time = df.format(Calendar.getInstance().getTime());

            Log.i("time", "uploadFile: "+time);



            ApiInterface getResponse = RetrofitClient.getClient().create(ApiInterface.class);
            Call<String> call = getResponse.uploadQuizCall(SUBJECT_ID, file.getName(), file.getName(),provider, quzDeadline, time, requestBody);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                    if (response.isSuccessful()){

                        String message = response.body();

                        addQuizProgressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Task uploaded successfully", Toast.LENGTH_SHORT).show();

//                        onBackPressed();
                        dialog.hide();

                    }else {
                        addQuizProgressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Task uploading unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.v("Response gotten is", t.getMessage());
                    Toast.makeText(getApplicationContext(), "problem uploading file " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }


            });
        }
    }

    ///////////////////////////////////////////////////////////////////////
    public void rbClick(View view) {
        int rbId = radioGroup.getCheckedRadioButtonId();
        RadioButton rbChoised = (RadioButton) view.findViewById(rbId);
        provider = rbChoised.getText().toString();
        Log.i("provider", "rbClick: "+provider);
    }

}

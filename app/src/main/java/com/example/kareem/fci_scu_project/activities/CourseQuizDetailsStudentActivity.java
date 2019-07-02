package com.example.kareem.fci_scu_project.activities;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kareem.fci_scu_project.R;

import static com.example.kareem.fci_scu_project.Helpers.Constants.TASK_DATA;

public class CourseQuizDetailsStudentActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 11;
    private Button selectSolButton;
    private Button downloadButton;
    private Button sendButton;
    private TextView quizType;
    private TextView solutionName;
    private TextView quizDeadline;
    private String solName;

    private DownloadManager downloadManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_quiz_details_student);

        getSupportActionBar().setTitle(TASK_DATA.getTaskName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        selectSolButton = findViewById(R.id.course_quiz_det_stud_selectBtn);
        downloadButton = findViewById(R.id.course_quiz_det_stud_downloadBtn);
        sendButton = findViewById(R.id.course_quiz_det_stud_sendBtn);
        solutionName = findViewById(R.id.course_quiz_det_stud_solutionName);

        quizDeadline = findViewById(R.id.course_quiz_det_stud_deadline);
        quizType = findViewById(R.id.course_quiz_det_stud_quizType);

        quizDeadline.setText(TASK_DATA.getDeadlineDate() +" at " + TASK_DATA.getDeadlineTime());
        quizType.setText(TASK_DATA.getProvider());

        selectSolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileChooserAction();
            }
        });

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String taskName = TASK_DATA.getTaskName();

                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse("https://matehub.azurewebsites.net/Data/Tasks/" + taskName+".pdf");
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setVisibleInDownloadsUi(true);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, uri.getLastPathSegment());
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                long reference = downloadManager.enqueue(request);
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //handle send btn here ....
                onBackPressed();
            }
        });

    }
    public void fileChooserAction(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("application/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){

            if (data != null){
                Uri uri = data.getData();
                Toast.makeText(this, data.getDataString(), Toast.LENGTH_LONG).show();
                solName = uri.getPath().substring(uri.getPath().lastIndexOf("/")+1);
                //Toast.makeText(this, solName, Toast.LENGTH_SHORT).show();
                solutionName.setText(solName.toString());

            }
        }
    }
}

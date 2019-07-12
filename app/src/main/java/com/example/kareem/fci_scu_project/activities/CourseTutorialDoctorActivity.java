package com.example.kareem.fci_scu_project.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
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
import com.example.kareem.fci_scu_project.adapters.CourseTutorialDoctorRVAdapter;
import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.adapters.CourseTutorialsStudentRVAdapter;
import com.example.kareem.fci_scu_project.classes.Material;
import com.example.kareem.fci_scu_project.classes.MaterialResponse;
import com.example.kareem.fci_scu_project.classes.Tutorial;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.kareem.fci_scu_project.Helpers.Constants.SUBJECT_ID;
import static com.example.kareem.fci_scu_project.Helpers.Constants.TASK_DATA;
import static com.example.kareem.fci_scu_project.Helpers.Constants.USER_DATA;

public class CourseTutorialDoctorActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 11;
    private List<Material> materialList;
    private RecyclerView tutorialRecyclerView;
    private CourseTutorialDoctorRVAdapter rvAdapter;
    private FloatingActionButton fab;
    private TextView tutorialName;
    private String tutName;
    private MaterialResponse materialResponse;
    private ProgressBar progressBar,addTutProgressBar;
    private String filePath;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_tutorial_doctor);

        getSupportActionBar().setTitle("Tutorials");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab = findViewById(R.id.course_tutorial_doc_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddTutorialDialog();
            }
        });

        progressBar = findViewById(R.id.Tut_doc_progressBar);
        progressBar.setVisibility(View.VISIBLE);

        getMaterials();

    }

    private void getMaterials(){

        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);

        Call<MaterialResponse> call = apiInterface.getMaterialsCall(SUBJECT_ID);
        call.enqueue(new Callback<MaterialResponse>() {
            @Override
            public void onResponse(Call<MaterialResponse> call, Response<MaterialResponse> response) {
                materialResponse = response.body();
                boolean status = materialResponse.getStatus();

                if (status) {

                    materialList = materialResponse.getMaterial();

                    tutorialRecyclerView = findViewById(R.id.course_tutorials_doc_recyclerview);
                    rvAdapter = new CourseTutorialDoctorRVAdapter(getBaseContext(),materialList,progressBar);
                    tutorialRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    tutorialRecyclerView.setAdapter(rvAdapter);

//                        Toast.makeText(getActivity().getBaseContext(), ""+subjectList.size(), Toast.LENGTH_SHORT).show();

                } else {
                    String message = response.message();
                    Toast.makeText(getBaseContext(), "please reload this page", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<MaterialResponse> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getBaseContext(), "please reload this page", Toast.LENGTH_SHORT).show();
                Log.i("Error", "onFailure: "+t.getMessage());
            }
        });
    }


    public void showAddTutorialDialog() {

        dialog = new Dialog(this);
        Button selectButton;
        Button addButton;

        dialog.setContentView(R.layout.course_add_tutorial_layout);
        Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null) {
            dialog.getWindow().getAttributes().width = getResources().getDisplayMetrics().widthPixels;
        }
        dialog.show();

        selectButton = dialog.findViewById(R.id.course_add_tutorial_selectbtn);
        addButton = dialog.findViewById(R.id.course_add_tutorial_addbtn);
        tutorialName = dialog.findViewById(R.id.course_add_tutorial_name);
        addTutProgressBar = dialog.findViewById(R.id.add_tut_progressBar);

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
                //adding tutorial here.
                Material material = new Material();
                material.setName(tutName);
                materialList.add(material);
                rvAdapter.notifyDataSetChanged();
                uploadFile();

            }
        });
    }
//    public void fileChooserAction(){
//        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//        intent.setType("application/*");
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        startActivityForResult(intent,REQUEST_CODE);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
//
//            if (data != null){
//                Uri uri = data.getData();
//                tutName = uri.getPath().substring(uri.getPath().lastIndexOf("/")+1);
//                tutorialName.setText(tutName);
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
                .withTitle("Select Material file")
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
                tutName = getFileName(uri);
                tutorialName.setText(tutName);
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

            addTutProgressBar.setVisibility(View.VISIBLE);

            File file = new File(filePath);
            // Parsing any Media type file

            RequestBody requestBody = RequestBody.create(MediaType.parse("application/*"), file);

            ApiInterface getResponse = RetrofitClient.getClient().create(ApiInterface.class);
            Call<String> call = getResponse.uploadMaterialCall(SUBJECT_ID, file.getName(),file.getTotalSpace(), requestBody);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                    if (response.isSuccessful()){

                        String message = response.body();

                        addTutProgressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Material uploaded successfully", Toast.LENGTH_SHORT).show();

//                        onBackPressed();
                        dialog.hide();

                    }else {
                        addTutProgressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Material uploading unsuccessful", Toast.LENGTH_SHORT).show();
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
}

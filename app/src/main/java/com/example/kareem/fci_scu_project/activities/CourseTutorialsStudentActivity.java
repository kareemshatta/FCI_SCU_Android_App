package com.example.kareem.fci_scu_project.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.Retrofit.ApiInterface;
import com.example.kareem.fci_scu_project.Retrofit.RetrofitClient;
import com.example.kareem.fci_scu_project.adapters.CourseTutorialsStudentRVAdapter;
import com.example.kareem.fci_scu_project.classes.Material;
import com.example.kareem.fci_scu_project.classes.MaterialResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.kareem.fci_scu_project.Helpers.Constants.SUBJECT_ID;

public class CourseTutorialsStudentActivity extends AppCompatActivity{

    private List<Material> materialList;
    private RecyclerView tutorialRecyclerView;
    private MaterialResponse materialResponse;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_tutorials_student);

        getSupportActionBar().setTitle("Tutorials");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.tut_stud_progressBar);
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

                    tutorialRecyclerView = findViewById(R.id.course_tutorials_student_recyclerview);
                    CourseTutorialsStudentRVAdapter rvAdapter = new CourseTutorialsStudentRVAdapter(getBaseContext(),materialList);
                    tutorialRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    tutorialRecyclerView.setAdapter(rvAdapter);

//                        Toast.makeText(getActivity().getBaseContext(), ""+subjectList.size(), Toast.LENGTH_SHORT).show();

                    progressBar.setVisibility(View.INVISIBLE);

                } else {
                    String message = response.message();
                    Toast.makeText(getBaseContext(), "Error : " + message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<MaterialResponse> call, Throwable t) {
                Log.i("Error", "onFailure: "+t.getMessage());
            }
        });
    }

}

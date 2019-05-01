package com.example.kareem.fci_scu_project.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kareem.fci_scu_project.Retrofit.ApiInterface;
import com.example.kareem.fci_scu_project.Retrofit.RetrofitClient;
import com.example.kareem.fci_scu_project.activities.LoginActivity;
import com.example.kareem.fci_scu_project.classes.Course;
import com.example.kareem.fci_scu_project.adapters.CoursesRecyclerViewAdapter;
import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.classes.Subject;
import com.example.kareem.fci_scu_project.classes.SubjectsResponse;
import com.example.kareem.fci_scu_project.classes.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.kareem.fci_scu_project.Helpers.Constants.USER_DATA;


/**
 * A simple {@link Fragment} subclass.
 */
public class CoursesFragment extends android.support.v4.app.Fragment {

    private RecyclerView coursesRecyclerView;
    private ArrayList<Subject> subjectList;
    private SubjectsResponse subjectsResponse;
    private ProgressBar progressBar;

    public CoursesFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_courses, container, false);
        // retrive course data from database later

        coursesRecyclerView = view.findViewById(R.id.courses_recyclerview);
        progressBar = view.findViewById(R.id.courses_fragment_progressBar);
        progressBar.setVisibility(View.VISIBLE);

        getSubjects();

        return view;
    }

    private void getSubjects(){

        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);

        Call<SubjectsResponse> call = apiInterface.getSubjectsCall(USER_DATA.getId());
        call.enqueue(new Callback<SubjectsResponse>() {
            @Override
            public void onResponse(Call<SubjectsResponse> call, Response<SubjectsResponse> response) {
                subjectsResponse = response.body();
                boolean status = subjectsResponse.getStatus();

                if (status) {


                    subjectList = subjectsResponse.getSubjects();
//                        Toast.makeText(getActivity().getBaseContext(), ""+subjectList.size(), Toast.LENGTH_SHORT).show();

                    CoursesRecyclerViewAdapter myAdapter = new CoursesRecyclerViewAdapter(getActivity(),subjectList);
                    coursesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    coursesRecyclerView.setAdapter(myAdapter);

                } else {
                    String message = response.message();
                    Toast.makeText(getActivity().getBaseContext(), "Error : " + message, Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<SubjectsResponse> call, Throwable t) {
                Log.i("Error", "onFailure: "+t.getMessage());
            }
        });
    }
}

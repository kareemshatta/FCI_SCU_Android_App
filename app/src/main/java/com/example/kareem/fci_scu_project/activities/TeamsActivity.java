package com.example.kareem.fci_scu_project.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kareem.fci_scu_project.Helpers.Constants;
import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.Retrofit.ApiInterface;
import com.example.kareem.fci_scu_project.Retrofit.RetrofitClient;
import com.example.kareem.fci_scu_project.adapters.TeamsAdapter;
import com.example.kareem.fci_scu_project.classes.Subject;
import com.example.kareem.fci_scu_project.classes.Team;
import com.example.kareem.fci_scu_project.classes.TeamsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamsActivity extends AppCompatActivity {
    public View view;
    public RecyclerView recyclerView;
    TeamsAdapter adapter;
    List<Team> teamList;
    private ProgressBar teams_loading_pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        getSupportActionBar().setTitle("All Team");
        Toast.makeText(getBaseContext(),"subjectId :"+ Constants.SUBJECT_ID.toString(), Toast.LENGTH_SHORT).show();
        teamList = new ArrayList<>();
        teams_loading_pb = findViewById(R.id.teams_loading_pb);
        loadTeams();
        setupRecyclerView();

    }
    public void loadTeams() {

        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<TeamsResponse> call = apiInterface.getSubjectTeams(Constants.SUBJECT_ID.toString());
        call.enqueue(new Callback<TeamsResponse>() {
            @Override
            public void onResponse(Call<TeamsResponse> call, Response<TeamsResponse> response) {
                TeamsResponse teamsResponse = response.body();
                boolean status = teamsResponse.getStatus();
                if (status) {
                    teams_loading_pb.setVisibility(View.GONE);
                    teamList = teamsResponse.getTeamList();
                    if (!teamList.isEmpty()) {
                        setupRecyclerView();
                    } else {
                        teams_loading_pb.setVisibility(View.GONE);

                        Toast.makeText(getBaseContext(), "No Teams Available", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    teams_loading_pb.setVisibility(View.GONE);
                    Toast.makeText(getBaseContext(), "No Teams Available", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<TeamsResponse> call, Throwable t) {

            }
        });
    }

    public void setupRecyclerView() {
        recyclerView = findViewById(R.id.activity_teams_recyclerview);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TeamsAdapter(this, teamList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
//        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);

    }
}

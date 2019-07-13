package com.example.kareem.fci_scu_project.activities;

import android.content.Intent;
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
import com.example.kareem.fci_scu_project.classes.Team;
import com.example.kareem.fci_scu_project.classes.TeamDetails;
import com.example.kareem.fci_scu_project.classes.TeamsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.kareem.fci_scu_project.Helpers.Constants.SUBJECT_ID;
import static com.example.kareem.fci_scu_project.Helpers.Constants.USER_DATA;

public class TeamsActivity extends AppCompatActivity {
    public View view;
    public RecyclerView recyclerView;
    TeamsAdapter adapter;
    List<Team> teamListDoctor;
    List<Team> teamListStudent;
    private ProgressBar teams_loading_pb;
    boolean hasTeam =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        Toast.makeText(getBaseContext(),"subjectId :"+ Constants.SUBJECT_ID.toString(), Toast.LENGTH_SHORT).show();
        teamListDoctor = new ArrayList<>();
        teamListStudent = new ArrayList<>();

        teams_loading_pb = findViewById(R.id.teams_loading_pb);

        if(USER_DATA.getRole().equals("Students")) {
            getSupportActionBar().setTitle("Team Details");
            loadTeam(USER_DATA.getId(),SUBJECT_ID.toString());
            if(!hasTeam) {
                startActivity(new Intent(getBaseContext(), CreateTeamActivity.class));
            }

        }else{
            getSupportActionBar().setTitle("All Teams");
            loadTeams();
        }

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
                    teamListDoctor = teamsResponse.getTeamList();
                    if (!teamListDoctor.isEmpty()) {
                        setupRecyclerView(teamListDoctor);
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

    public void setupRecyclerView(List<Team> teamList) {
        recyclerView = findViewById(R.id.activity_teams_recyclerview);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TeamsAdapter(this, teamList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
//        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);

    }
    public void loadTeam(String userId, String subjectId) {
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<TeamDetails> call = apiInterface.hasAteam(userId,subjectId);
        call.enqueue(new Callback<TeamDetails>() {
            @Override
            public void onResponse(Call<TeamDetails> call, Response<TeamDetails> response) {
                TeamDetails teamResponse = response.body();
                Team team = new Team();
                team.setTeamId(0);
                team.setTeamName(teamResponse.getTeamName());
                boolean status = teamResponse.getStatus();
                if (status) {
                    teamListStudent.add(team);
                  setupRecyclerView(teamListStudent);
                } else {
                    Toast.makeText(getBaseContext(), "You dont have a team", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<TeamDetails> call, Throwable t) {

            }
        });
    }
}

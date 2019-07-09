package com.example.kareem.fci_scu_project.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.Retrofit.ApiInterface;
import com.example.kareem.fci_scu_project.Retrofit.RetrofitClient;
import com.example.kareem.fci_scu_project.classes.TeamDetails;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamDetailsActivity extends AppCompatActivity {
    TextView teams_details_doctor_team_name_tv,teams_details_doctor_leader_name_tv;
    ListView teams_details_doctor_members_lv;
    String teamId;
    TeamDetails team = null ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_details);
        getSupportActionBar().setTitle("Team Details");
        teamId = getIntent().getStringExtra("teamId");
        teams_details_doctor_leader_name_tv = findViewById(R.id.teams_details_doctor_leader_name_tv);
        teams_details_doctor_team_name_tv = findViewById(R.id.teams_details_doctor_team_name_tv);
        teams_details_doctor_members_lv = findViewById(R.id.teams_details_doctor_members_lv);
        loadTeamDetails();
    }
    public void loadTeamDetails() {

        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<TeamDetails> call = apiInterface.getTeamDetails(teamId);
        call.enqueue(new Callback<TeamDetails>() {
            @Override
            public void onResponse(Call<TeamDetails> call, Response<TeamDetails> response) {
                TeamDetails teamResponse = response.body();
                boolean status = teamResponse.getStatus();
                if (status) {
                    team = teamResponse;
                    if (!team.equals(null)) {
                        teams_details_doctor_leader_name_tv.setText(team.getLeaderName());
                        teams_details_doctor_team_name_tv.setText(team.getTeamName());
                        Toast.makeText(TeamDetailsActivity.this, teamId, Toast.LENGTH_SHORT).show();
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext() , android.R.layout.simple_list_item_1 ,team.getMembers());
                        teams_details_doctor_members_lv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getBaseContext(), "No Data Available", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(getBaseContext(), "No Data Available", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<TeamDetails> call, Throwable t) {

            }
        });
    }
}

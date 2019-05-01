package com.example.kareem.fci_scu_project.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kareem.fci_scu_project.R;

import java.util.ArrayList;

public class DoctorTeamsDetailsActivity extends AppCompatActivity {
    TextView teams_details_doctor_team_name_tv,teams_details_doctor_leader_name_tv;
    ListView teams_details_doctor_members_lv;
    LayoutInflater inflater;
    ArrayList<String> teamMembers;
     ArrayAdapter<String> adapter ;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_teams_details);
        getSupportActionBar().setTitle("Team Details");
          teamMembers = new ArrayList<>();
        teamMembers.add(0,"Abdo seddik");
        teamMembers.add(0,"hassan ali");
        teamMembers.add(0,"Abdo ibrahem");
        teamMembers.add(0,"ismail kareem");


        teams_details_doctor_leader_name_tv = findViewById(R.id.teams_details_doctor_leader_name_tv);
        teams_details_doctor_team_name_tv = findViewById(R.id.teams_details_doctor_team_name_tv);
        teams_details_doctor_members_lv = findViewById(R.id.teams_details_doctor_members_lv);


        teams_details_doctor_leader_name_tv.setText("abdo mohsen");
        teams_details_doctor_team_name_tv.setText(getIntent().getStringExtra("team_name"));

        // = inflater.inflate
        adapter = new ArrayAdapter(this , android.R.layout.simple_list_item_1 , teamMembers);
        teams_details_doctor_members_lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();




    }
}

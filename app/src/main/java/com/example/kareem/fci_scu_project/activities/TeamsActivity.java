package com.example.kareem.fci_scu_project.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.adapters.TeamsAdapter;
import com.example.kareem.fci_scu_project.model.Teams;

import java.util.ArrayList;

public class TeamsActivity extends AppCompatActivity {
    public View view;
    public RecyclerView recyclerView;
    TeamsAdapter adapter;
    ArrayList<Teams> teams = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        createPostList();
        createRecyclerView();

    }
    public void createPostList() {
        teams.add(new Teams(0, "Google"));
        teams.add(new Teams(1, "Group1"));
        teams.add(new Teams(2, "Group2"));
        teams.add(new Teams(0, "Google"));
        teams.add(new Teams(1, "Group1"));
        teams.add(new Teams(2, "Group2"));
        teams.add(new Teams(0, "Google"));
        teams.add(new Teams(1, "Group1"));
        teams.add(new Teams(2, "Group2"));
        teams.add(new Teams(0, "Google"));
        teams.add(new Teams(1, "Group1"));
        teams.add(new Teams(2, "Group2"));
        teams.add(new Teams(0, "Google"));
        teams.add(new Teams(1, "Group1"));
        teams.add(new Teams(2, "Group2"));


    }

    public void createRecyclerView() {
        recyclerView = findViewById(R.id.fragment_teams_recyclerview);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TeamsAdapter(this, teams);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}

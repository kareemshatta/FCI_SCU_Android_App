package com.example.kareem.fci_scu_project.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.activities.TeamDetailsActivity;
import com.example.kareem.fci_scu_project.classes.Team;
import com.google.gson.Gson;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by youssef on 13/2/2019.
 */

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolder> {

    Context context;

    List<Team> teamList;

    public TeamsAdapter(Context context, List<Team> teamList) {
        this.context = context;
        this.teamList = teamList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_teams_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Team team = teamList.get(position);
        Toast.makeText(context, "position :"+String.valueOf(position), Toast.LENGTH_SHORT).show();
        String teamName = team.getTeamName();
        holder.fragment_teams_row_name_tv.setText(teamName);
        holder.activity_teams_icon_group.setText((teamName.charAt(0) + "").toUpperCase());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TeamDetailsActivity.class);
                intent.putExtra("teamId",team.getTeamId().toString());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fragment_teams_row_name_tv;
        TextView activity_teams_icon_group;
        CardView fragment_teams_row_cv;

        public ViewHolder(View view) {
            super(view);
            fragment_teams_row_name_tv = view.findViewById(R.id.activity_teams_row_name_tv);
            fragment_teams_row_cv = view.findViewById(R.id.activity_teams_row_cv);
            activity_teams_icon_group = view.findViewById(R.id.activity_teams_icon_group);

        }
    }
}
package com.example.kareem.fci_scu_project.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.activities.DoctorTeamsDetailsActivity;
import com.example.kareem.fci_scu_project.model.Teams;


import java.util.ArrayList;

/**
 * Created by youssef on 13/2/2019.
 */

public class TeamsFragmentAdapter extends RecyclerView.Adapter<TeamsFragmentAdapter.ViewHolder> {

    Context context;
    FragmentManager fragmentManager;
    Teams team;
    ArrayList<Teams> teams;

    public TeamsFragmentAdapter(Context context, ArrayList<Teams> teams) {
        this.context = context;
        this.teams = teams;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_teams_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        team = teams.get(position);
        final String teamName = team.getName().toString();
        holder.fragment_teams_row_name_tv.setText(teamName);
        holder.fragment_teams_row_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DoctorTeamsDetailsActivity.class);
                intent.putExtra("team_name",teamName);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fragment_teams_row_name_tv;
        CardView fragment_teams_row_cv;

        public ViewHolder(View view) {
            super(view);
            fragment_teams_row_name_tv = view.findViewById(R.id.fragment_teams_row_name_tv);
            fragment_teams_row_cv = view.findViewById(R.id.fragment_teams_row_cv);

        }
    }
}
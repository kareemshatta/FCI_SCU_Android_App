package com.example.kareem.fci_scu_project.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.adapters.TeamsFragmentAdapter;
import com.example.kareem.fci_scu_project.model.Teams;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeamsFragment extends Fragment {


    public View view;
    public RecyclerView recyclerView;
    TeamsFragmentAdapter adapter;
    ArrayList<Teams> teams = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        createPostList();
        createRecyclerView(inflater,container,savedInstanceState);
        return view;

    }
    public void createPostList() {
        teams.add(new Teams(0,"Google"));
        teams.add(new Teams(1,"Group1"));
        teams.add(new Teams(2,"Group2"));


    }

    public void createRecyclerView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_teams, container, false);
        recyclerView = view.findViewById(R.id.fragment_teams_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        adapter = new TeamsFragmentAdapter(getActivity(), teams);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}

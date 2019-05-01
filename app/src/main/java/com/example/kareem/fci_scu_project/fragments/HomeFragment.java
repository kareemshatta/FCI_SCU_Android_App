package com.example.kareem.fci_scu_project.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kareem.fci_scu_project.activities.HomeActivity;
import com.example.kareem.fci_scu_project.adapters.HomeFragmentAdapter;
import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.model.PostModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public View view;
    public RecyclerView recyclerView;
    public ArrayList<String> comment = new ArrayList<>();
    public ArrayList<PostModel> postModels = new ArrayList<>();
    public HomeFragmentAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        HomeActivity.fab.setVisibility(View.VISIBLE);

        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        FloatingActionButton fab = new HomeActivity().fab;
        fab.setVisibility(View.VISIBLE);
        createPostList();
        createRecyclerView(inflater, container, savedInstanceState);
        return view;
    }

    public void createPostList() {
        createCommentList();
        postModels.add(new PostModel(1
                , 9
                , comment
                , "Mohamed Hassan"
                , "2 hrs"
                , "The cars we drive say a lot about us." +
                "The cars we drive say a lot about us."));

        postModels.add(new PostModel(2
                , 26
                , comment
                , "Youssef Seddik"
                , "9 hrs"
                , "Don't be afraid of your fears. They're not there to scare you. They're there to \n" +
                "let you know that something is worth it."));
    }

    public void createRecyclerView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.homeRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomeFragmentAdapter(getContext(), postModels, getFragmentManager());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

//        adapter.setOnItemClickListner(new HomeFragmentAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int postion) {
//                postModels.get(postion).;
//            }
//        });
    }

    public void createCommentList() {
        comment.add("i love that");
        comment.add("i love that");
        comment.add("i love that");
        comment.add("i love that");
        comment.add("i love that");
    }
}


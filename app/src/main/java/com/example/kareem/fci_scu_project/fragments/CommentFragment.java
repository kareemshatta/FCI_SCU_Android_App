package com.example.kareem.fci_scu_project.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.kareem.fci_scu_project.adapters.CommentFragmentAdapter;
import com.example.kareem.fci_scu_project.activities.HomeActivity;
import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.model.PostModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommentFragment extends Fragment {
    ArrayList<String> comment = new ArrayList<>();
    RecyclerView recyclerView;
    CommentFragmentAdapter adapter;
    PostModel postModel;
    EditText comment_fragment_comment;
    Button comment_fragment_comment_send;
    FloatingActionButton fab = new HomeActivity().fab;
    View view;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_comment, container, false);
        fab.setVisibility(View.INVISIBLE);
        comment_fragment_comment = view.findViewById(R.id.comment_fragment_comment);
        comment_fragment_comment_send = view.findViewById(R.id.comment_fragment_send);

        createPostItem();
        createRecyclerView();

        comment_fragment_comment_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertItem();
            }
        });
        return view;

    }

    public void createPostItem() {
        comment.add(0, "this is good");
        comment.add(1, "i want that");
        postModel = new PostModel(1
                , 9
                , comment
                , "kareem Seddik"
                , "2 hrs"
                , "The cars we drive say a lot about us.");

    }

    public void createRecyclerView() {
        recyclerView = view.findViewById(R.id.comment_fragment_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CommentFragmentAdapter(getContext(), postModel, getFragmentManager());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void insertItem() {
        int position = comment.size();
        comment.add(position, comment_fragment_comment.getText().toString());
        comment_fragment_comment.setText("");
        adapter.notifyItemInserted(position);
    }
}

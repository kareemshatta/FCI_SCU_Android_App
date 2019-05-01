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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kareem.fci_scu_project.Helpers.Constants;
import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.Retrofit.ApiInterface;
import com.example.kareem.fci_scu_project.Retrofit.RetrofitClient;
import com.example.kareem.fci_scu_project.activities.HomeActivity;
import com.example.kareem.fci_scu_project.adapters.CommentFragmentAdapter;
import com.example.kareem.fci_scu_project.adapters.HomeFragmentAdapter;
import com.example.kareem.fci_scu_project.classes.Comment;
import com.example.kareem.fci_scu_project.classes.CommentResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommentFragment extends Fragment {
    RecyclerView recyclerView;
    EditText comment_fragment_comment;
    Button comment_fragment_comment_send;
    FloatingActionButton fab = new HomeActivity().fab;
    CommentFragmentAdapter commentFragmentAdapter;
    View view;
    List<Comment> comments = null;
    private int postId;
    private ProgressBar comment_loading_pb;




    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_comment, container, false);
        fab.setVisibility(View.INVISIBLE);
        comment_fragment_comment = view.findViewById(R.id.comment_fragment_comment);
        comment_fragment_comment_send = view.findViewById(R.id.comment_fragment_send);
        comment_loading_pb = view.findViewById(R.id.comment_loading_pb);
        recyclerView = view.findViewById(R.id.comment_fragment_recyclerview);
        loadComments(postId);
        comment_fragment_comment_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentText = comment_fragment_comment.getText().toString();
                addComment(Constants.USER_DATA.getId(),String.valueOf(postId),commentText);
            }
        });


        return view;

    }


    public void loadComments(int postId){
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<CommentResponse> call = apiInterface.getCommentsCall(String.valueOf(postId));
        call.enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                CommentResponse commentResponse = response.body();
                boolean status = commentResponse.getStatus();
                if(status){
                    comments = commentResponse.getResponse();
                    if(!comments.isEmpty()) {
                        comment_loading_pb.setVisibility(View.GONE);
                        commentFragmentAdapter = new CommentFragmentAdapter(getContext(), (ArrayList<Comment>) comments, getFragmentManager());
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(commentFragmentAdapter);
                        commentFragmentAdapter.notifyDataSetChanged();
                    }else {
                        comment_loading_pb.setVisibility(View.GONE);
                        Toast.makeText(getContext(),"No Comments Available",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    comment_loading_pb.setVisibility(View.GONE);
                    Toast.makeText(getContext(),"No Comments Available",Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {

            }
        });

    }
    public void addComment(String userId, final String postId, String content) {
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.addComment(userId, postId, content);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String responseRecieved = response.body();
                if (responseRecieved.equals("done")) {
                    commentFragmentAdapter = new CommentFragmentAdapter(getContext(), (ArrayList<Comment>) comments, getFragmentManager());
                    Toast.makeText(getContext(), "Comment Added", Toast.LENGTH_SHORT).show();
                    HomeFragmentAdapter.refreshCommentNum(1);
                    loadComments(Integer.parseInt(postId));
                    commentFragmentAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();

                }



            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }

    public void setPostId(int postid) {
        this.postId = postid;
    }


}

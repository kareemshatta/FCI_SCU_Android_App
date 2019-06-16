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
import android.widget.ProgressBar;

import com.example.kareem.fci_scu_project.Helpers.Constants;
import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.Retrofit.ApiInterface;
import com.example.kareem.fci_scu_project.Retrofit.RetrofitClient;
import com.example.kareem.fci_scu_project.activities.HomeActivity;
import com.example.kareem.fci_scu_project.adapters.HomeFragmentAdapter;
import com.example.kareem.fci_scu_project.classes.Post;
import com.example.kareem.fci_scu_project.classes.PostResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public View view;
    public RecyclerView recyclerView;
    public HomeFragmentAdapter adapter;
    private ProgressBar home_post_loading_pb;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        HomeActivity.fab.setVisibility(View.VISIBLE);
        super.onCreate(savedInstanceState);
        loadPosts();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        FloatingActionButton fab = new HomeActivity().fab;
        fab.setVisibility(View.VISIBLE);
        loadPosts();
        view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.homeRecyclerView);
        home_post_loading_pb = view.findViewById(R.id.home_post_loading_pb);
        return view;
    }

    public void loadPosts() {
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<PostResponse> call = apiInterface.getPostsCall(Constants.USER_DATA.getId());

        call.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                PostResponse postResponse = response.body();
                boolean status = postResponse.getStatus();
                if (status) {
                    home_post_loading_pb.setVisibility(View.GONE);
                    List<Post> posts = postResponse.getResponse();
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(layoutManager);
                    adapter = new HomeFragmentAdapter(getContext(), (ArrayList<Post>) posts, getFragmentManager());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {

            }
        });
    }

    public void deletePost(int postid) {
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.deletePost(String.valueOf(postid));
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {


            }
        });
    }

}


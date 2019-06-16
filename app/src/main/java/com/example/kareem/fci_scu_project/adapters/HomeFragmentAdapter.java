package com.example.kareem.fci_scu_project.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.Retrofit.ApiInterface;
import com.example.kareem.fci_scu_project.Retrofit.RetrofitClient;
import com.example.kareem.fci_scu_project.classes.Post;
import com.example.kareem.fci_scu_project.classes.User;
import com.example.kareem.fci_scu_project.fragments.CommentFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by youssef on 1/2/2019.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Post> postList;
    private Post post;
    private FragmentManager fragmentManager;
    private static int commentNum=0;

    public static void refreshCommentNum(int count){
        commentNum+= count;
    }

    public HomeFragmentAdapter(Context context, ArrayList<Post> postList, FragmentManager fragmentManager) {
        this.context = context;
        this.postList = postList;
        this.fragmentManager = fragmentManager;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Drawable shape1 = context.getDrawable(R.drawable.home_fragment_like_btn);
        final Drawable shape2 = context.getDrawable(R.drawable.home_fragment_delike_btn);
        final CommentFragment commentFragment = new CommentFragment();
        post = postList.get(position);
        Log.e("go", "post: " + post);

        commentFragment.setPostId(post.getPostId());
        commentNum = post.getCommentNo();
        holder.home_comment_count_tv.setText(String.valueOf(commentNum) + " comment");
        holder.home_like_count_tv.setText(post.getLikeNo().toString());
        if (post.getLikeState() == true) {
            holder.home_fragment_like_btn.setBackground(shape1);
        } else {
            holder.home_fragment_like_btn.setBackground(shape2);
        }

        User user = new User();
        user.setId(post.getUserId());
        holder.home_name_tv.setText("Youssef Seddik");


        String date1 = post.getAddedOn();
        Log.e("go", "Date: " + date1);
        //Toast.makeText(context, post.getAddedOn(), Toast.LENGTH_LONG).show();


        String time = post.getAddedOn();
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long startDate = date.getTime();
        String agoTime = post.getTimeAgo(startDate);
        holder.home_post_time_tv.setText(agoTime);


        holder.home_post_tv.setText(post.getContent());
//        holder.home_profile_picture_iv.set

        holder.fragment_home_comment_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().addToBackStack("CommentFragment")
                        .add(R.id.main_container, commentFragment).commit();

            }
        });


        holder.fragment_home_like_ly.setOnClickListener(new View.OnClickListener() {
              int countlike = 0 ;
            @Override
            public void onClick(View view) {
                Post post = postList.get(position);
                Toast.makeText(context, post.getPostId().toString(), Toast.LENGTH_SHORT).show();
                boolean likestatus = post.getLikeState();

                if (likestatus == false) {
                    countlike = Integer.parseInt(post.getLikeNo().toString());
                    holder.home_fragment_like_btn.setBackground(shape1);
                    likestatus = true;
                    post.setLikeState(likestatus);
                    countlike+=1;
                    holder.home_like_count_tv.setText(String.valueOf(countlike));
                    addLike(post.getUserId(),post.getPostId().toString());


                }
                else if (likestatus == true) {
                    countlike = Integer.parseInt(post.getLikeNo().toString());
                    holder.home_fragment_like_btn.setBackground(shape2);
                    likestatus = false;
                    post.setLikeState(likestatus);
                    countlike-=1;
                    if (countlike < 0) {
                        countlike = 0;
                    }
                    holder.home_like_count_tv.setText(String.valueOf(countlike));
                    removeLike(post.getUserId(),post.getPostId().toString());

                }

            }
        }

        );
    }


    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView home_name_tv, home_post_time_tv, home_post_tv, home_like_count_tv, home_comment_count_tv;
        ImageView home_profile_picture_iv;
        Button home_fragment_like_btn, home_fragment_comment_btn;
        LinearLayout fragment_home_like_ly, fragment_home_comment_ly;

        public MyViewHolder(View itemView) {
            super(itemView);
            home_name_tv = itemView.findViewById(R.id.home_fragment_profile_name);
            home_post_time_tv = itemView.findViewById(R.id.home_fragment_post_time);
            home_post_tv = itemView.findViewById(R.id.home_fragment_post);
            home_like_count_tv = itemView.findViewById(R.id.home_fragment_like_count);
            home_comment_count_tv = itemView.findViewById(R.id.home_fragment_comment_count);
            home_profile_picture_iv = itemView.findViewById(R.id.home_fragment_profile_picture);
            home_fragment_like_btn = itemView.findViewById(R.id.home_fragment_like_btn);
            home_fragment_comment_btn = itemView.findViewById(R.id.home_fragment_comment_btn);
            fragment_home_like_ly = itemView.findViewById(R.id.fragment_home_like_ly);
            fragment_home_comment_ly = itemView.findViewById(R.id.fragment_home_comment_ly);


        }

    }

    public void addLike(String userId, String postId) {
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.addPostLike(userId, postId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String responseRecieved = response.body();
                if (responseRecieved.equals("done")) {
                    Toast.makeText(context, "Like Added", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void removeLike(String userId, String postId) {
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.removePostLike(userId, postId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String responseRecieved = response.body();
                if (responseRecieved.equals("done")) {
                    Toast.makeText(context, "Like Removed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

}


package com.example.kareem.fci_scu_project.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.fragments.CommentFragment;
import com.example.kareem.fci_scu_project.model.PostModel;


import java.util.ArrayList;

/**
 * Created by youssef on 1/2/2019.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.MyViewHolder> {

     boolean like = false;
    Context context;
    ArrayList<PostModel> postModels = new ArrayList<>();
    PostModel model;
    FragmentManager fragmentManager;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int postion);
    }
    public void setOnItemClickListner(OnItemClickListener listner){
        this.listener = listner;
    }




    public HomeFragmentAdapter(Context context, ArrayList<PostModel> postModels, FragmentManager fragmentManager) {
        this.context = context;
        this.postModels = postModels;
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

        model = postModels.get(position);
        holder.home_comment_count_tv.setText(String.valueOf(CommentFragmentAdapter.count) + " comment");
        holder.home_like_count_tv.setText(String.valueOf(model.getLikes()));
        holder.home_name_tv.setText(model.getName());
        holder.home_post_time_tv.setText(model.getTime());
        holder.home_post_tv.setText(model.getPost().toString());

        holder.fragment_home_comment_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().addToBackStack("CommentFragment")
                        .replace(R.id.main_container, new CommentFragment()).commit();

            }
        });


        holder.fragment_home_like_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable shape1 = context.getDrawable(R.drawable.home_fragment_like_btn);
                Drawable shape2 = context.getDrawable(R.drawable.home_fragment_delike_btn);
                int count = Integer.parseInt(holder.home_like_count_tv.getText().toString());
                if (like == false) {
                    holder.home_fragment_like_btn.setBackground(shape1);
                    like = true;
                    count++;
                    holder.home_like_count_tv.setText(String.valueOf(count));


                } else if (like == true) {

                    holder.home_fragment_like_btn.setBackground(shape2);
                    like = false;
                    count--;
                    holder.home_like_count_tv.setText(String.valueOf(count));
                }


            }


        });


    }

    @Override
    public int getItemCount() {
        return postModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView home_name_tv, home_post_time_tv, home_post_tv, home_like_count_tv, home_comment_count_tv;
        ImageView home_profile_picture_iv;
        Button home_fragment_like_btn, home_fragment_comment_btn;
        LinearLayout fragment_home_like_ly,fragment_home_comment_ly;

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
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(listener != null){
//                        int position = getAdapterPosition();
//                        if(position != RecyclerView.NO_POSITION){
//                            listener.onItemClick(position);
//                        }
//                    }
//
//                }
//            });

        }
    }


}


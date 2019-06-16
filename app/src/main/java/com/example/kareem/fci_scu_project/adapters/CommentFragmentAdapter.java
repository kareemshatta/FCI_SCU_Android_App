package com.example.kareem.fci_scu_project.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.classes.Comment;
import com.example.kareem.fci_scu_project.model.PostModel;

import java.util.ArrayList;

/**
 * Created by youssef on 6/2/2019.
 */

public class CommentFragmentAdapter extends RecyclerView.Adapter<CommentFragmentAdapter.OkViewHolder> {
    static int count;

    Context context;
    FragmentManager fragmentManager;
    PostModel model;
    ArrayList<Comment> comments;

    public CommentFragmentAdapter(Context context, ArrayList<Comment> comments, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.comments = comments;



    }

    @NonNull
    @Override
    public OkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_comment_row, parent, false);
        return new OkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OkViewHolder holder, int position) {
        Comment comment = comments.get(position);
//        holder.comment_fragment_profile.setText(comment.);
        holder.comment_fraggment_text.setText(comment.getContent());


    }

    @Override
    public int getItemCount() {
        count = comments.size();
        return count;
    }

    public class OkViewHolder extends RecyclerView.ViewHolder {
        TextView comment_fragment_profile;
        TextView comment_fraggment_text;


        public OkViewHolder(View itemView) {
            super(itemView);
            comment_fragment_profile = itemView.findViewById(R.id.comment_fragment_profile);
            comment_fraggment_text = itemView.findViewById(R.id.comment_fragment_text);


        }
    }



}
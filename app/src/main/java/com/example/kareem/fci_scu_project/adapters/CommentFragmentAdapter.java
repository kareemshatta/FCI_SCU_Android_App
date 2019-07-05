package com.example.kareem.fci_scu_project.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kareem.fci_scu_project.Helpers.Constants;
import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.classes.Comment;
import com.example.kareem.fci_scu_project.classes.Post;
import com.example.kareem.fci_scu_project.classes.User;
import com.example.kareem.fci_scu_project.model.PostModel;

import java.util.ArrayList;

import static com.example.kareem.fci_scu_project.Helpers.Constants.USER_DATA;

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
        holder.comment_fragment_text.setText(comment.getContent());

        User user = Constants.USER_DATA;
        String url = "https://matehub.azurewebsites.net";
        if(user.getProfilePicture() != null){
            url = url.concat(USER_DATA.getProfilePicture().substring(1));
            Glide.with(context)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.mipmap.boss)
                    .into(holder.comment_fragment_profile_picture);
        }

        holder.comment_fragment_profile_name.setText(user.getUserName());

    }

    @Override
    public int getItemCount() {
        count = comments.size();
        return count;
    }

    public class OkViewHolder extends RecyclerView.ViewHolder {
        TextView comment_fragment_profile_name;
        TextView comment_fragment_text;
        ImageView comment_fragment_profile_picture;


        public OkViewHolder(View itemView) {
            super(itemView);
            comment_fragment_profile_name = itemView.findViewById(R.id.comment_fragment_profile);
            comment_fragment_text = itemView.findViewById(R.id.comment_fragment_text);
            comment_fragment_profile_picture = itemView.findViewById(R.id.comment_fragment_profile_picture);


        }
    }



}
package com.example.kareem.fci_scu_project.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.classes.Notification;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationRVAdapter  extends RecyclerView.Adapter<NotificationRVAdapter.MyViewHolder>{

    private Context context;
    public List<Notification> notificationList;

    public NotificationRVAdapter(Context context, List<Notification> notificationList) {
        this.context = context;
        this.notificationList = notificationList;
    }

    @Override
    public NotificationRVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.notification_row_layout,parent,false);

        return new NotificationRVAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NotificationRVAdapter.MyViewHolder holder, final int position) {
        holder.notifUsername.setText(notificationList.get(position).getUsername());
        holder.notifContent.setText(notificationList.get(position).getContent());
        //holder.notifUserImage.setImageResource(R.drawable.ic_user_image);

    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView notifUsername;
        TextView notifContent;
        CircleImageView notifUserImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            notifUsername = itemView.findViewById(R.id.notification_username);
            notifContent = itemView.findViewById(R.id.notification_content);
            notifUserImage = itemView.findViewById(R.id.notification_userImageView);
        }

    }



}

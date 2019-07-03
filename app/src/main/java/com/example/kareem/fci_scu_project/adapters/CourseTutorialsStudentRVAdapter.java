package com.example.kareem.fci_scu_project.adapters;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.activities.CourseTutorialsStudentActivity;
import com.example.kareem.fci_scu_project.classes.Material;
import com.example.kareem.fci_scu_project.classes.Tutorial;

import java.util.List;

import static android.support.v4.content.ContextCompat.checkSelfPermission;

public class CourseTutorialsStudentRVAdapter extends RecyclerView.Adapter<CourseTutorialsStudentRVAdapter.MyViewHolder> {

    private Context context;
    private List<Material> materialList;
    private DownloadManager downloadManager;

    public CourseTutorialsStudentRVAdapter(Context context, List<Material> materialList) {
        this.context = context;
        this.materialList = materialList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.course_tutorials_student_layout,parent,false);

        return new CourseTutorialsStudentRVAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final String materialName = materialList.get(position).getName();
        holder.tutorialName.setText(materialName);
        holder.downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //haveStoragePermission();
                downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse("https://matehub.azurewebsites.net/Data/Materials/" + materialName);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setVisibleInDownloadsUi(true);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, uri.getLastPathSegment());
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                long reference = downloadManager.enqueue(request);

            }
        });


    }
    public  boolean haveStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(context,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission error","You have permission");
                return true;
            } else {

                Log.e("Permission error","You have asked for permission");
                Activity activity = (Activity) context;
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //you dont need to worry about these stuff below api level 23
            Log.e("Permission error","You already have the permission");
            return true;
        }
    }
    @Override
    public int getItemCount() {
        return materialList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tutorialName;
        ImageButton downloadBtn;

        public MyViewHolder(View itemView) {
            super(itemView);
            tutorialName = itemView.findViewById(R.id.course_tutorials_stud_tutorialName);
            downloadBtn = itemView.findViewById(R.id.course_tutorials_stud_imageButton);


        }
    }
}

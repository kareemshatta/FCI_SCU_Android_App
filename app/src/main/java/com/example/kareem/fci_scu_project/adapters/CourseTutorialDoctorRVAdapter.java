package com.example.kareem.fci_scu_project.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.Retrofit.ApiInterface;
import com.example.kareem.fci_scu_project.Retrofit.RetrofitClient;
import com.example.kareem.fci_scu_project.classes.Material;
import com.example.kareem.fci_scu_project.classes.Tutorial;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseTutorialDoctorRVAdapter extends RecyclerView.Adapter<CourseTutorialDoctorRVAdapter.MyViewHolder>{

    private Context context;
    public List<Material> materialList;
    private ProgressBar progressBar;

    public CourseTutorialDoctorRVAdapter(Context context, List<Material> materialList,ProgressBar progressBar) {
        this.context = context;
        this.materialList = materialList;
        this.progressBar = progressBar;
    }

    @Override
    public CourseTutorialDoctorRVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.course_tutorial_doctor_layout,parent,false);

        return new CourseTutorialDoctorRVAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tutorialName.setText(materialList.get(position).getName());
        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);

                ApiInterface getResponse = RetrofitClient.getClient().create(ApiInterface.class);
                Call<String> call = getResponse.deleteMaterialCall(materialList.get(position).getMaterialId());

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        if (response.isSuccessful()){

                            progressBar.setVisibility(View.INVISIBLE);

                            String message = response.body();

                            materialList.remove(position);
                            notifyDataSetChanged();

                            Toast.makeText(context, "Delete task is "+message, Toast.LENGTH_SHORT).show();


                        }else {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(context, "Task deleting unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.v("Response gotten is", t.getMessage());
                        Toast.makeText(context, "problem deleting task " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                });


            }
        });
    }

    @Override
    public int getItemCount() {
        return materialList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tutorialName;
        ImageButton deleteItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            tutorialName = itemView.findViewById(R.id.course_tutorials_doc_tutorialName);
            deleteItem = itemView.findViewById(R.id.course_tutorials_doc_imageButton);
        }

    }
}

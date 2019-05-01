package com.example.kareem.fci_scu_project.Retrofit;

import com.example.kareem.fci_scu_project.classes.LoginResponse;
import com.example.kareem.fci_scu_project.classes.MaterialResponse;
import com.example.kareem.fci_scu_project.classes.SubjectsResponse;
import com.example.kareem.fci_scu_project.classes.TasksResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("login")
    Call<LoginResponse> getUserCall(@Query("name") String name , @Query("pass") String pass);

    @GET("subjects")
    Call<SubjectsResponse> getSubjectsCall(@Query("userid") String userId);

    @GET("materials")
    Call<MaterialResponse> getMaterialsCall(@Query("subjectid") Integer subjectId);

    @GET("tasks")
    Call<TasksResponse> getTasksCall(@Query("subjectid") Integer subjectId);

//mstany eldesha

    @POST("forgetPassword")
    Call<Boolean>forgetPasswordCall(@Query("email") String email);

    @POST("editpassword")
    Call<Boolean> editPasswordCall(@Query("userid") String userId , @Query("newpass") String newPass);




}

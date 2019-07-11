package com.example.kareem.fci_scu_project.Retrofit;

import com.example.kareem.fci_scu_project.classes.CommentResponse;
import com.example.kareem.fci_scu_project.classes.ForgetResponse;
import com.example.kareem.fci_scu_project.classes.LoginResponse;
import com.example.kareem.fci_scu_project.classes.MaterialResponse;
import com.example.kareem.fci_scu_project.classes.PostResponse;
import com.example.kareem.fci_scu_project.classes.SubjectsResponse;
import com.example.kareem.fci_scu_project.classes.TasksResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("login")
    Call<LoginResponse> getUserCall(@Query("name") String name, @Query("pass") String pass);

    @GET("subjects")
    Call<SubjectsResponse> getSubjectsCall(@Query("userid") String userId);

    @GET("materials")
    Call<MaterialResponse> getMaterialsCall(@Query("subjectid") Integer subjectId);

    @GET("tasks")
    Call<TasksResponse> getTasksCall(@Query("subjectid") Integer subjectId);

    @POST("forgetpassword")
    Call<String> forgetPasswordCall(@Query("email") String email);

    @POST("changepassword")
    Call<ForgetResponse> editPasswordCall(@Query("userid") String userId, @Query("password") String newPass);

    @GET("posts")
    Call<PostResponse> getPostsCall(@Query("userid") String userId);

    @GET("comments")
    Call<CommentResponse> getCommentsCall(@Query("postid") String postId);

    @POST("createpost")
    Call<String> createPost(@Query("userid") String userId, @Query("content") String content);

    @POST("addlike")
    Call<String> addPostLike(@Query("userid") String userId, @Query("postid") String postid);

    @POST("removelike")
    Call<String> removePostLike(@Query("userid") String userId, @Query("postid") String postid);

    @POST("deletepost")
    Call<String> deletePost(@Query("postid") String postid);

    @POST("addcomment")
    Call<String> addComment(@Query("userid") String userId, @Query("postid") String postid, @Query("content") String content);

//    @Multipart
//    @POST("uploadtasksolution")
//    Call<String> uploadSolution(@Query("taskid") Integer taskId, @Query("userid") String userId, @Part MultipartBody.Part file, @Part("file") RequestBody name);


    @POST("uploadtasksolution")
    Call<String> uploadSolution(@Query("taskid") Integer taskId, @Query("userid") String userId, @Query("filename") String filename, @Body RequestBody requestBody);


    @POST("uploadmaterials")
    Call<String> uploadMaterialCall(@Query("subjectid") Integer subjectId, @Query("filename") String filename, @Query("filesize") Long fileSize, @Body RequestBody requestBody);



    @POST("addtask")
    Call<String> uploadQuizCall(@Query("subjectid") Integer subjectId, @Query("filename") String filename, @Query("taskname") String taskName,@Query("taskprovider") String taskProvider, @Query("date") String date ,@Query("time") String time, @Body RequestBody requestBody);

    @POST("deletematerial")
    Call<String> deleteMaterialCall(@Query("id") Integer materialId);

    @POST("deletetask")
    Call<String> deleteTaskCall(@Query("id") Integer taskId);

}
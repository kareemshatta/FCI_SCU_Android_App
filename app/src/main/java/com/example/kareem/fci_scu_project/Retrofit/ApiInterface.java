package com.example.kareem.fci_scu_project.Retrofit;

import com.example.kareem.fci_scu_project.classes.CommentResponse;
import com.example.kareem.fci_scu_project.classes.CreateTeamResponse;
import com.example.kareem.fci_scu_project.classes.LoginResponse;
import com.example.kareem.fci_scu_project.classes.MaterialResponse;
import com.example.kareem.fci_scu_project.classes.PostResponse;
import com.example.kareem.fci_scu_project.classes.SubjectsResponse;
import com.example.kareem.fci_scu_project.classes.TasksResponse;
import com.example.kareem.fci_scu_project.classes.Team;
import com.example.kareem.fci_scu_project.classes.TeamDetails;
import com.example.kareem.fci_scu_project.classes.TeamsResponse;
import com.example.kareem.fci_scu_project.classes.UserResponse;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
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

//mstany eldesha

    @POST("forgetPassword")
    Call<Boolean> forgetPasswordCall(@Query("email") String email);

    @POST("editpassword")
    Call<Boolean> editPasswordCall(@Query("userid") String userId, @Query("newpass") String newPass);

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

    @Multipart
    @POST("uploadtasksolution")
    Call<String> upload(@Query("taskid") Integer taskId, @PartMap Map<String, RequestBody> map);

    @GET("users")
    Call<UserResponse> getStudents();

    @POST("createteam")
    Call<CreateTeamResponse> createTeam(@Query("subjectid") String subjectid, @Query("leaderid") String leaderid, @Query("teamname") String teamname, @Query("members") List<String> members);


    @GET("allteams")
    Call<TeamsResponse> getSubjectTeams(@Query("subjectid") String userId);

    @GET("team")
    Call<TeamDetails> getTeamDetails(@Query("teamid") String teamid);

    @GET("studentteam")
    Call<TeamDetails> hasAteam(@Query("userid") String userid,@Query("subjectid") String subjectid);

}

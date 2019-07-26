package com.example.kareem.fci_scu_project.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.Retrofit.ApiInterface;
import com.example.kareem.fci_scu_project.Retrofit.RetrofitClient;
import com.example.kareem.fci_scu_project.adapters.SelectStudentAdapter;
import com.example.kareem.fci_scu_project.classes.StudentRule;
import com.example.kareem.fci_scu_project.classes.User;
import com.example.kareem.fci_scu_project.classes.UserResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class SelectStudentActivity extends AppCompatActivity {

    static final int PICK_CONTACT_REQUEST = 1;
    Button ResultBtn;
    List<String> listIsSelected = null;
    List<User> userList;
    List<StudentRule> studentRuleList;
    SelectStudentAdapter adapter;
    private ProgressBar select_student_loading_pb;
    RecyclerView recyclerView;
    ArrayList<StudentRule> filteredData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_student);
        select_student_loading_pb = findViewById(R.id.select_student_loading_pb);
        ResultBtn = findViewById(R.id.ResultBtn);
        listIsSelected = new ArrayList<>();
        studentRuleList = new ArrayList<>();
        userList = new ArrayList<>();
        getSupportActionBar().setTitle(R.string.select_student_txt);
        setUpRecyclerView();
        ResultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (StudentRule item : studentRuleList) {
                    if (item.isSelected()) {
                        listIsSelected.add(item.getUserName()+"/"+item.getId());
                        Log.e("listIsSelected", "name : " + item.getUserName() + "Id : " + item.getId());
                    }
                }
                int selected = 0;
                for (StudentRule item : studentRuleList) {

                    if (!item.isSelected()) {
                        selected++;
                    }
                }
                if (selected == studentRuleList.size()) {
                    Toast.makeText(SelectStudentActivity.this, "Please Select", Toast.LENGTH_SHORT).show();
                }else{
                    if(getIntent().getStringExtra("requestCode").equals("2")) {
                        Intent returnIntent = new Intent();
                        returnIntent.putStringArrayListExtra("result", (ArrayList<String>) listIsSelected);
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    }else {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result",listIsSelected.get(0));
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    }
                }


            }

        });
    }




    private void setUpRecyclerView() {
        loadStudents();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SelectStudentActivity.this);
        adapter = new SelectStudentAdapter(SelectStudentActivity.this, studentRuleList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
    public void loadStudents() {
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<UserResponse> call = apiInterface.getStudents();
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();

                boolean status = userResponse.getStatus();
                if (status) {
                    userList = userResponse.getResponse();

                    if (!userList.isEmpty()) {
                        select_student_loading_pb.setVisibility(View.GONE);
                        for (User item : userList) {
                            if (item.getRole().equals("Students")) {
                                studentRuleList.add(new StudentRule(false, item.getUserName(), item.getId()));

                            }
                        }
                    }else {
                        select_student_loading_pb.setVisibility(View.GONE);
                        Toast.makeText(SelectStudentActivity.this,"No Student Available",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    select_student_loading_pb.setVisibility(View.GONE);
                    Toast.makeText(SelectStudentActivity.this,"No Student Available",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filteredData = new ArrayList<>();

                for (StudentRule forumData : studentRuleList) {
                    if (forumData.getUserName().toLowerCase().contains(newText.toLowerCase())) {

                        filteredData.add(forumData);

                    }

                }
                adapter = new SelectStudentAdapter(SelectStudentActivity.this,filteredData);
                recyclerView.setAdapter(adapter);
                return true;

            }
        });

        return true;
    }
}

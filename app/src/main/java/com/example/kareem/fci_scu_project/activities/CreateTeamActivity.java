package com.example.kareem.fci_scu_project.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kareem.fci_scu_project.Helpers.Constants;
import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.Retrofit.ApiInterface;
import com.example.kareem.fci_scu_project.Retrofit.RetrofitClient;
import com.example.kareem.fci_scu_project.fragments.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateTeamActivity extends AppCompatActivity {
    String requestCodeLeader = "1";
    String requestCodemember = "2";
    public  String teamLeaderId = "";
    public List<String> teamMembersId;
    public static List<String> teamMembersNames;
    public  String subjectId;
    public static String teamName = "";
    List<String> listIsSelected;
    EditText activity_create_team_name_et;
    Button activity_create_team_create_btn, activity_create_team_member_btn, activity_create_team_leader_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);
        getSupportActionBar().setTitle(R.string.create_team_nav_txt);
        activity_create_team_name_et = findViewById(R.id.fragment_create_team_name_et);
        activity_create_team_create_btn = findViewById(R.id.fragment_create_team_create_btn);
        activity_create_team_member_btn = findViewById(R.id.fragment_create_team_member_btn);
        activity_create_team_leader_btn = findViewById(R.id.fragment_create_team_leader_btn);
        teamMembersId = new ArrayList<>();
        teamMembersNames = new ArrayList<>();
        subjectId = Constants.SUBJECT_ID.toString();
        activity_create_team_leader_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), SelectStudentActivity.class);
                intent.putExtra("requestCode", requestCodeLeader);
                startActivityForResult(intent, 1);
                activity_create_team_leader_btn.setError(null);


            }
        });

        activity_create_team_member_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamMembersNames.clear();
                Intent intent = new Intent(getBaseContext(), SelectStudentActivity.class);
                intent.putExtra("requestCode", requestCodemember);
                startActivityForResult(intent, 2);
                activity_create_team_member_btn.setError(null);


            }
        });
        activity_create_team_create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamName = activity_create_team_name_et.getText().toString().trim();
                if (teamName.equals("")) {
                    activity_create_team_name_et.setError("Please enter teamName");
                    Toast.makeText(CreateTeamActivity.this, "Please enter teamName", Toast.LENGTH_SHORT).show();

                } else if (teamLeaderId.equals("")) {
                    activity_create_team_leader_btn.setError("Please select teamLeader");
                    Toast.makeText(CreateTeamActivity.this, "Please select teamLeader", Toast.LENGTH_SHORT).show();

                } else if (teamMembersId.isEmpty()) {
                    activity_create_team_member_btn.setError("Please select teamMembers");
                    Toast.makeText(CreateTeamActivity.this, "Please select teamMembers", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(CreateTeamActivity.this, "SubjectId: "+subjectId, Toast.LENGTH_SHORT).show();

                    createTeam(subjectId,teamName,teamLeaderId,teamMembersId);
                }



            }
        });

    }

    private void createTeam(String subjectId, String teamName, String teamLeaderId, List<String> teamMembersId) {
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.createTeam(subjectId,teamLeaderId,teamName,teamMembersId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(retrofit2.Call call, Response response) {
                String responseReceived = response.body().toString();
                Log.e("createTeam", "onResponse: "+response.body().toString());
                if (responseReceived.equals("done")) {
                    Toast.makeText(getBaseContext(), "Team is Created ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "Team not Created ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(retrofit2.Call call, Throwable t) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
                String[] list = result.split("/");
                teamLeaderId = list[1].trim();
                Log.e("id", "TeamLeaderId: " + teamLeaderId);
                activity_create_team_leader_btn.setText(list[0]);


            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Please select team leader", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                ArrayList<String> result = data.getStringArrayListExtra("result");

                for (String item : result) {
                    String[] namesIds = item.split("/");
                    teamMembersId.add(namesIds[1].trim());
                    Log.e("id", "TeamLeaderId: " + teamMembersId.toString());
                    teamMembersNames.add(namesIds[0].trim());
                }
                activity_create_team_member_btn.setText("Team Members");
                setUpListView(teamMembersNames);

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Please select team members", Toast.LENGTH_SHORT).show();

            }

        }
    }

    private void setUpListView(List<String> teamMembersNames) {

        ListView listView = findViewById(R.id.fragment_create_team_member_selected_lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                CreateTeamActivity.this,
                android.R.layout.simple_list_item_1,
                teamMembersNames);
        listView.setAdapter(adapter);
    }


}

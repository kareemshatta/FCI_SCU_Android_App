package com.example.kareem.fci_scu_project.activities;

import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.expandable.CheckedFragment;
import com.example.kareem.fci_scu_project.fragments.SelectStudentFragment;

public class CreateTeamActivity extends AppCompatActivity {

    EditText fragment_create_team_name_et;
    TextView fragment_create_team_leader_selected_tv, fragment_create_team_member_selected_tv;
    Button fragment_create_team_create_btn, fragment_create_team_member_btn, fragment_create_team_leader_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);
        fragment_create_team_name_et = findViewById(R.id.fragment_create_team_name_et);
        fragment_create_team_leader_selected_tv = findViewById(R.id.fragment_create_team_leader_selected_tv);
        fragment_create_team_member_selected_tv = findViewById(R.id.fragment_create_team_member_selected_tv);
        fragment_create_team_create_btn = findViewById(R.id.fragment_create_team_create_btn);
        fragment_create_team_member_btn = findViewById(R.id.fragment_create_team_member_btn);
        fragment_create_team_leader_btn = findViewById(R.id.fragment_create_team_leader_btn);

        fragment_create_team_leader_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                replaceFragment(new SelectStudentFragment(),"SelectStudentFragment");

//                fragment_create_team_leader_selected_tv.setText(CheckedFragment.parent);
            }
        });

        fragment_create_team_member_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                replaceFragment(new SelectStudentFragment(),"SelectStudentFragment");

                fragment_create_team_leader_selected_tv.setText(CheckedFragment.parent);


            }
        });

    }

    public void replaceFragment(Fragment fragment, String tag) {
      getSupportFragmentManager().beginTransaction().
              add(fragment, tag).
              addToBackStack(tag).
              commit();
    }
}

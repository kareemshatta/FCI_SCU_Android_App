package com.example.kareem.fci_scu_project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kareem.fci_scu_project.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.kareem.fci_scu_project.Helpers.Constants.COURSE_NAME;
import static com.example.kareem.fci_scu_project.Helpers.Constants.SUBJECT_ID;
import static com.example.kareem.fci_scu_project.Helpers.Constants.USER_ROLE;

public class CourseDetailsActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button tutorialsBtn;
    private Button quizesBtn;
    private Button teamsBtn;

    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);


        getSupportActionBar().setTitle(COURSE_NAME);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        flag = getIntent().getStringExtra("flag");
        HomeActivity.coursesBack = 1;

        tutorialsBtn = findViewById(R.id.course_details_tutorials_btn);
        quizesBtn = findViewById(R.id.course_details_quizes_btn);
        teamsBtn = findViewById(R.id.course_details_teams_btn);
        spinner = findViewById(R.id.course_details_spinner);

        Toast.makeText(this, USER_ROLE, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, SUBJECT_ID.toString(), Toast.LENGTH_SHORT).show();


        List<String> listOFYears = new ArrayList<>();
        listOFYears.add("2018/2019");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOFYears);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner.setSelection(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tutorialsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (USER_ROLE.equals("Students")) {
                    //for student
                    startActivity(new Intent(getBaseContext(), CourseTutorialsStudentActivity.class));
                } else {
                    //for doctor
                    startActivity(new Intent(getBaseContext(), CourseTutorialDoctorActivity.class));
                }


            }
        });


        quizesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (USER_ROLE.equals("Students")) {
                    //for student
                    startActivity(new Intent(getBaseContext(), CourseQuizStudentActivity.class));
                } else {
                    //for doctor
                    startActivity(new Intent(getBaseContext(), CourseQuizDoctorActivity.class));
                }
            }
        });
        teamsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (USER_ROLE.equals("Students")) {
                    //for student
                    startActivity(new Intent(getBaseContext(), CreateTeamActivity.class));
                } else {
                    //for doctor
                    startActivity(new Intent(getBaseContext(), TeamsActivity.class));
                }


            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();

    }

}

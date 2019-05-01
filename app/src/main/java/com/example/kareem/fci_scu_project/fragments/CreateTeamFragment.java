package com.example.kareem.fci_scu_project.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.expandable.CheckedFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateTeamFragment extends Fragment {
    View view;
    public static int flag = 0;
    EditText fragment_create_team_name_et;
    TextView fragment_create_team_leader_selected_tv, fragment_create_team_member_selected_tv;
    Button fragment_create_team_create_btn, fragment_create_team_member_btn, fragment_create_team_leader_btn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_team, container, false);
        View return_selected = inflater.inflate(R.layout.fragment_select_student, container, false);
        final Button btn = return_selected.findViewById(R.id.btn);


        fragment_create_team_name_et = view.findViewById(R.id.fragment_create_team_name_et);
        fragment_create_team_leader_selected_tv = view.findViewById(R.id.fragment_create_team_leader_selected_tv);
        fragment_create_team_member_selected_tv = view.findViewById(R.id.fragment_create_team_member_selected_tv);
        fragment_create_team_create_btn = view.findViewById(R.id.fragment_create_team_create_btn);
        fragment_create_team_member_btn = view.findViewById(R.id.fragment_create_team_member_btn);
        fragment_create_team_leader_btn = view.findViewById(R.id.fragment_create_team_leader_btn);


        fragment_create_team_leader_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //replaceFragment(new SelectStudentFragment());


                //new
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_container, new SelectStudentFragment(), "SelectStudentFragment")
                        .addToBackStack("SelectStudentFragment")
                        .commit();
                fragment_create_team_leader_selected_tv.setText(CheckedFragment.parent);
            }
        });

        fragment_create_team_member_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //replaceFragment(new SelectStudentFragment());

                //new
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_container, new SelectStudentFragment(), "SelectStudentFragment")
                        .addToBackStack("SelectStudentFragment")
                        .commit();

                fragment_create_team_leader_selected_tv.setText(CheckedFragment.parent);


            }
        });


        return view;
    }
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }

}

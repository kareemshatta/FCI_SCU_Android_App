package com.example.kareem.fci_scu_project.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.example.kareem.fci_scu_project.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment{

    private Button editPassBtn;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        editPassBtn = view.findViewById(R.id.profile_fragment_edit_password_btn);
        editPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditPasswordDialog();
            }
        });

        return view;
    }

    public void showEditPasswordDialog(){
        Dialog Dialog = new Dialog(getContext());
        Dialog.setContentView(R.layout.profile_edit_password_layout);
        Window dialogWindow = Dialog.getWindow();

        if (dialogWindow != null) {
            Dialog.getWindow().getAttributes().width = getResources().getDisplayMetrics().widthPixels;
        }
        Dialog.show();
    }
}

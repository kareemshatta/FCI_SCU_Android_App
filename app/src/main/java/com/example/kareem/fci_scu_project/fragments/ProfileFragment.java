package com.example.kareem.fci_scu_project.fragments;


import android.animation.Animator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.Retrofit.ApiInterface;
import com.example.kareem.fci_scu_project.Retrofit.RetrofitClient;
import com.example.kareem.fci_scu_project.adapters.CoursesRecyclerViewAdapter;
import com.example.kareem.fci_scu_project.classes.SubjectsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.kareem.fci_scu_project.Helpers.Constants.USER_DATA;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment{

    private TextView userName , email , password
            , phone ,birthDate , idNum , dept , level;
    private ImageView userImage;
    private Button editPassBtn;
    private ProgressBar progressBar;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        progressBar = view.findViewById(R.id.profile_fragment_progressBar);
        progressBar.setVisibility(View.VISIBLE);


        editPassBtn = view.findViewById(R.id.profile_fragment_edit_password_btn);
        editPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditPasswordDialog();
            }
        });

        userImage = view.findViewById(R.id.profile_fragment_user_image);
        userName = view.findViewById(R.id.profile_fragment_userName);
        email = view.findViewById(R.id.profile_fragment_email);
        password = view.findViewById(R.id.profile_fragment_password);
        phone = view.findViewById(R.id.profile_fragment_phone);
        birthDate = view.findViewById(R.id.profile_fragment_birthdate);
        idNum = view.findViewById(R.id.profile_fragment_idNum);
        dept = view.findViewById(R.id.profile_fragment_department);
        level = view.findViewById(R.id.profile_fragment_level);

        setUserData();

        return view;
    }

    private void setUserData(){

        String url = "https://cdn.shopify.com/s/files/1/0066/6976/2673/articles/cf33c5dd40ea98a6fc7925f1a76001de--guy-style-style-men.jpg?v=1547701975";
        Glide.with(this)
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.default_image)
                .into(userImage);

        userName.setText(USER_DATA.getUserName());
        email.setText(USER_DATA.getEmail());
        password.setText(USER_DATA.getPassword());
        phone.setText(USER_DATA.getPhoneNumber());
//        birthDate.setText(USER_DATA.getBirthDate());
//        idNum.setText(USER_DATA.getIdNum());
//        dept.setText(USER_DATA.getDept());
        level.setText(USER_DATA.getLevel());
//        progressBar.setVisibility(View.INVISIBLE);

    }

    public void showEditPasswordDialog(){
        final EditText oldPass , newPass , confirmNewPass;
        Button saveBtn;
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.profile_edit_password_layout);
        Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null) {
            dialog.getWindow().getAttributes().width = getResources().getDisplayMetrics().widthPixels;
        }
        dialog.show();
        oldPass = dialog.findViewById(R.id.old_password);
        newPass = dialog.findViewById(R.id.new_password);
        confirmNewPass = dialog.findViewById(R.id.confirm_new_password);
        saveBtn = dialog.findViewById(R.id.save_password_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!oldPass.getText().toString().equals(USER_DATA.getPassword())) {
                    oldPass.setError(getResources().getString(R.string.error_old_pass_validation));
                }else if (!newPass.getText().toString().equals(confirmNewPass.getText().toString())) {
                    confirmNewPass.setError(getResources().getString(R.string.error_confirm_pass_validation));
                }else {

                    ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);

                    Call<Boolean> call = apiInterface.editPasswordCall(USER_DATA.getId() ,newPass.getText().toString());
                    call.enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
//                        subjectsResponse = response.body();
//                        boolean status = subjectsResponse.getStatus();
//
//                        if (status) {
//
//
//                            subjectList = subjectsResponse.getSubjects();
////                        Toast.makeText(getActivity().getBaseContext(), ""+subjectList.size(), Toast.LENGTH_SHORT).show();
//
//                        } else {
//                            String message = response.message();
//                            Toast.makeText(getActivity().getBaseContext(), "Error : " + message, Toast.LENGTH_SHORT).show();
//                        }
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Log.i("Error", "onFailure: "+t.getMessage());
                        }
                    });
                }

            }

        });


    }
}

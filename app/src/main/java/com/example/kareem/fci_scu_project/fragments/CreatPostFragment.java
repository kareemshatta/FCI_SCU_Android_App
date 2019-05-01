package com.example.kareem.fci_scu_project.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.model.PostModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreatPostFragment extends Fragment {
    public String s;
    public ArrayList<String> comment = new ArrayList<>();
    public PostModel postModel;
    public HomeFragment homeFragment;
    public View view;
    public Button fragment_create_post_public_btn, fragment_create_post_private_btn, fragment_create_post_btn;
    public EditText fragment_create_post_content_tv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createItemView(inflater, container, savedInstanceState);
        handleBtnActions();
        return view;
    }

    public void createItemView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_post, container, false);
        fragment_create_post_public_btn = view.findViewById(R.id.fragment_create_post_public_btn);
        fragment_create_post_private_btn = view.findViewById(R.id.fragment_create_post_private_btn);
        fragment_create_post_btn = view.findViewById(R.id.fragment_create_post_btn);
        fragment_create_post_content_tv = view.findViewById(R.id.fragment_create_post_content_tv);


    }

    public void createPostItem() {
        String s = fragment_create_post_content_tv.getText().toString();
        createCommentItem();
        postModel = new PostModel(1
                , 9
                , comment
                , "John"
                , "2 hrs"
                , s);
    }

    public void createCommentItem() {
        comment.add("i love that");
    }


    public void handleBtnActions() {
        fragment_create_post_private_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               //old replaceFragment(new SelectStudentFragment());

                //new
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_container, new SelectStudentFragment(), "SelectStudentFragment")
                        .addToBackStack("SelectStudentFragment")
                        .commit();
                //new


            }
        });


        fragment_create_post_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                createPostItem();

                homeFragment = new HomeFragment();
                homeFragment.postModels.add(0, postModel);
                replaceFragment(homeFragment);
            }
        });
    }
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }

}



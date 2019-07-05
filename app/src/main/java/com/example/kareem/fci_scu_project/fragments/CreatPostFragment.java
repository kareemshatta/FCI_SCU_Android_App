package com.example.kareem.fci_scu_project.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kareem.fci_scu_project.Helpers.Constants;
import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.Retrofit.ApiInterface;
import com.example.kareem.fci_scu_project.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.kareem.fci_scu_project.Helpers.Constants.USER_DATA;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreatPostFragment extends Fragment {

    private HomeFragment homeFragment;
    private View view;
    private Button fragment_create_post_btn;
    private EditText fragment_create_post_content_tv;
    private TextView fragment_create_post_profile_name_tv;
    private ImageView fragment_create_post_profile_picture_iv;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_post, container, false);

        fragment_create_post_btn = view.findViewById(R.id.fragment_create_post_btn);
        fragment_create_post_content_tv = view.findViewById(R.id.fragment_create_post_content_tv);
        fragment_create_post_profile_name_tv = view.findViewById(R.id.fragment_create_post_profile_name_tv);
        fragment_create_post_profile_picture_iv = view.findViewById(R.id.fragment_create_post_profile_picture_iv);

        fragment_create_post_profile_name_tv.setText(Constants.USER_DATA.getUserName());

        String url = "https://matehub.azurewebsites.net";
        if(USER_DATA.getProfilePicture() != null){
            url = url.concat(USER_DATA.getProfilePicture().substring(1));
            Glide.with(getContext())
                    .load(url)
                    .centerCrop()
                    .placeholder(R.mipmap.boss)
                    .into(fragment_create_post_profile_picture_iv);
        }
        fragment_create_post_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                createPostCall(Constants.USER_DATA.getId(),fragment_create_post_content_tv.getText().toString());
            }
        });
        return view;
    }

    public void createPostCall(String userId ,String content) {
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.createPost(userId, content);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String responseReceived = response.body();
                if (responseReceived.equals("done")) {
                    getFragmentManager().beginTransaction().addToBackStack("HomeFragment")
                            .replace(R.id.main_container, new HomeFragment()).commit();
                    Toast.makeText(getContext(), "Post Created ", Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}


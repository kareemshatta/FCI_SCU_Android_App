package com.example.kareem.fci_scu_project.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.Retrofit.ApiInterface;
import com.example.kareem.fci_scu_project.Retrofit.RetrofitClient;
import com.example.kareem.fci_scu_project.classes.ForgetResponse;
import com.example.kareem.fci_scu_project.classes.LoginResponse;
import com.example.kareem.fci_scu_project.classes.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.kareem.fci_scu_project.Helpers.Constants.KEY_USER_DATA;
import static com.example.kareem.fci_scu_project.Helpers.Constants.KEY_USER_ROLE;
import static com.example.kareem.fci_scu_project.Helpers.Constants.PREF_KEY;
import static com.example.kareem.fci_scu_project.Helpers.Constants.PREF_USER_LOGGED;
import static com.example.kareem.fci_scu_project.Helpers.Constants.USER_DATA;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button loginBtn;
    private Button loginForgetBtn;
    private String flag = null;
    private EditText userName , password;
    private String name , pass;
    private User user;
    private LoginResponse loginResponse;
    private ForgetResponse forgetResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        loginBtn = findViewById(R.id.login_btn);
        loginForgetBtn = findViewById(R.id.login_forget_btn);
        loginBtn.setOnClickListener(this);
        loginForgetBtn.setOnClickListener(this);
        userName = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);

    }

    public void showForgetPasswordDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.forget_password_layout);
        Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null) {
            dialog.getWindow().getAttributes().width = getResources().getDisplayMetrics().widthPixels;
        }

        final EditText et_email = dialog.findViewById(R.id.forget_password_textview);
        Button btnSend = dialog.findViewById(R.id.forget_password_send_btn);

        dialog.show();


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = et_email.getText().toString().trim();
                //Toast.makeText(LoginActivity.this, email, Toast.LENGTH_SHORT).show();

                if (email.isEmpty()) {
                    et_email.setError(getResources().getString(R.string.error_email_validation));
                } else {

                    ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);

                    Call<ForgetResponse> call = apiInterface.forgetPasswordCall(email);
                    call.enqueue(new Callback<ForgetResponse>() {
                        @Override
                        public void onResponse(Call<ForgetResponse> call, Response<ForgetResponse> response) {
                            forgetResponse = response.body();
                            boolean status = forgetResponse.getStatus();

                            if (status) {

                                Toast.makeText(getBaseContext(), "Check your inbox", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }else{
                                Toast.makeText(getBaseContext(), "Try again...", Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<ForgetResponse> call, Throwable t) {
                            Toast.makeText(getBaseContext(), "unsuccessful process", Toast.LENGTH_SHORT).show();

                        }

                    });
                }
            }

        });
    }
    boolean vaildateData() {
        boolean isValid = true;
        if (name == null || name.matches("")) {
            isValid = false;
            userName.setError(getResources().getString(R.string.error_user_name_validation));
        }

        if (pass == null || pass.matches("")) {
            isValid = false;
            password.setError(getResources().getString(R.string.error_pass_validation));
        }

        return isValid;
    }
    private void saveToPref(){
        User userData = user;
        USER_DATA = userData;
        Log.i("uuuuuu", "saveToPref: "+USER_DATA.getUserName());

//        Toast.makeText(this, "dddd  "+userData.getLevel(), Toast.LENGTH_SHORT).show();

        SharedPreferences sharedpreferences = getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedpreferences.edit();
        prefsEditor.putString(KEY_USER_DATA, new Gson().toJson(userData));
        prefsEditor.putString(KEY_USER_ROLE, userData.getRole());
        prefsEditor.putBoolean(PREF_USER_LOGGED, true);
        prefsEditor.apply();




        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
        intent.putExtra("flag","doctor");
        startActivity(intent);
        finish();

    }
    private void login(){

        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<LoginResponse> call = apiInterface.getUserCall(name,pass);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                loginResponse = response.body();
                boolean status = loginResponse.getStatus();

                if (status) {

                    user = loginResponse.getUser();
                    saveToPref();

                } else {
                    String message = response.message();
                    Toast.makeText(LoginActivity.this, "Error : " + message, Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.i("Error", "onFailure: " + t.getMessage());
            }
        });

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.login_btn){

            name = userName.getText().toString().trim();
            pass = password.getText().toString();
            if (vaildateData()){
                login();

            }




//            if(userName.getText().toString().equals("student") && password.getText().toString().equals("123456")){
//                flag = "student";
//            }else{
//                flag = "doctor";
//            }
//            Intent intent = new Intent(this,HomeActivity.class);
//            intent.putExtra("flag",flag);
//            startActivity(intent);
//            finish();

        }else if(view.getId() == R.id.login_forget_btn){
            showForgetPasswordDialog();
        }
    }
}
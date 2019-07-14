package com.example.kareem.fci_scu_project.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {


    @SerializedName("Status")
    @Expose
    private Boolean status;
    @SerializedName("Response")
    @Expose
    private List<User> userList = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<User> getResponse() {
        return userList;
    }

    public void setResponse(List<User> response) {
        this.userList = response;
    }

}
package com.example.kareem.fci_scu_project.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostResponse {

    @SerializedName("Status")
    @Expose
    private boolean status;
    @SerializedName("Response")
    @Expose
    private List<Post> postList = null;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Post> getResponse() {
        return postList;
    }

    public void setResponse(List<Post> postList) {
        this.postList = postList;
    }

}
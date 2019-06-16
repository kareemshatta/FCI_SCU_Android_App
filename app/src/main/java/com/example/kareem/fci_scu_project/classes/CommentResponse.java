package com.example.kareem.fci_scu_project.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentResponse {

    @SerializedName("Status")
    @Expose
    private boolean status;
    @SerializedName("Response")
    @Expose
    private List<Comment> comment = null;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Comment> getResponse() {
        return comment;
    }

    public void setResponse(List<Comment> comment) {
        this.comment = comment;
    }

}

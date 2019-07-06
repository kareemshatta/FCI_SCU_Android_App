package com.example.kareem.fci_scu_project.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForgetResponse {
    @SerializedName("Status")
    @Expose
    private boolean status;
    @SerializedName("Response")
    @Expose
    private String response;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}

package com.example.kareem.fci_scu_project.classes;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SubjectsResponse {


    @SerializedName("Status")
    @Expose
    private Boolean status;

    @SerializedName("Response")
    @Expose
    private ArrayList<Subject> subjects;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }



}

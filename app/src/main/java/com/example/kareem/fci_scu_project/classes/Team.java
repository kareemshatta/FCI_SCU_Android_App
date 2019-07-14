package com.example.kareem.fci_scu_project.classes;

/**
 * Created by youssef on 13/2/2019.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Team {

    @SerializedName("TeamId")
    @Expose
    private Integer teamId;
    @SerializedName("TeamName")
    @Expose
    private String teamName;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

}

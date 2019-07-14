package com.example.kareem.fci_scu_project.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamDetails {

    @SerializedName("Status")
    @Expose
    private boolean status;
    @SerializedName("LeaderName")
    @Expose
    private String leaderName;
    @SerializedName("TeamName")
    @Expose
    private String teamName;
    @SerializedName("Members")
    @Expose
    private List<String> members = null;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

}
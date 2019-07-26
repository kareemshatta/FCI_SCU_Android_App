package com.example.kareem.fci_scu_project.classes;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamsResponse {

    @SerializedName("Status")
    @Expose
    private boolean status;
    @SerializedName("TeamsNo")
    @Expose
    private Integer teamsNo;

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    @SerializedName("Response")
    @Expose
    private List<Team> teamList = null;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getTeamsNo() {
        return teamsNo;
    }

    public void setTeamsNo(Integer teamsNo) {
        this.teamsNo = teamsNo;
    }



}
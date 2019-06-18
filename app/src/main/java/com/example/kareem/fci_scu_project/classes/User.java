package com.example.kareem.fci_scu_project.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("PhoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("National_Id")
    @Expose
    private String nationalId;
    @SerializedName("Role")
    @Expose
    private String role;
    @SerializedName("ProfilePicture")
    @Expose
    private String profilePicture;
    @SerializedName("Level")
    @Expose
    private String level;
    @SerializedName("AddedOn")
    @Expose
    private String addedOn;
    @SerializedName("Online")
    @Expose
    private Boolean online;
    @SerializedName("DisConnectedOn")
    @Expose
    private String disConnectedOn;
    @SerializedName("ConnectedOn")
    @Expose
    private Object connectedOn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getDisConnectedOn() {
        return disConnectedOn;
    }

    public void setDisConnectedOn(String disConnectedOn) {
        this.disConnectedOn = disConnectedOn;
    }

    public Object getConnectedOn() {
        return connectedOn;
    }

    public void setConnectedOn(Object connectedOn) {
        this.connectedOn = connectedOn;
    }

}
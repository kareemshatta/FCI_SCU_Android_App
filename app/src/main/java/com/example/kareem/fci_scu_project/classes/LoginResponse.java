package com.example.kareem.fci_scu_project.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginResponse {

        @SerializedName("Status")
        @Expose
        private Boolean status;
        @SerializedName("Response")
        @Expose
        private ArrayList<User> user;

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public User getUser() {
            return user.get(0);
        }

        public void setUser(User user) {
            this.user.set(0,user);
        }


}

package com.example.kareem.fci_scu_project.classes;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class CreateTeamResponse {

        @SerializedName("Status")
        @Expose
        private boolean status;
        @SerializedName("Response")
        @Expose
        private List<String> response = null;

        public boolean getStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public List<String> getResponse() {
            return response;
        }

        public void setResponse(List<String> response) {
            this.response = response;
        }

    }
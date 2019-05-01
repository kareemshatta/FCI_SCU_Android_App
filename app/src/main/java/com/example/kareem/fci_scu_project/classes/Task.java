package com.example.kareem.fci_scu_project.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Task {

    @SerializedName("Task_Id")
    @Expose
    private Integer taskId;
    @SerializedName("TaskName")
    @Expose
    private String taskName;
    @SerializedName("DeadlineDate")
    @Expose
    private String deadlineDate;
    @SerializedName("DeadlineTime")
    @Expose
    private String deadlineTime;
    @SerializedName("FileName")
    @Expose
    private String fileName;
    @SerializedName("Provider")
    @Expose
    private String provider;
    @SerializedName("AddedOn")
    @Expose
    private String addedOn;
    @SerializedName("Subject_Id")
    @Expose
    private Integer subjectId;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(String deadlineTime) {
        this.deadlineTime = deadlineTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

}
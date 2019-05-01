package com.example.kareem.fci_scu_project.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subject {

    @SerializedName("Subject_Id")
    @Expose
    private Integer subjectId;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Professor")
    @Expose
    private String professor;
    @SerializedName("Assistant")
    @Expose
    private String assistant;
    @SerializedName("Level")
    @Expose
    private String level;
    @SerializedName("Semester")
    @Expose
    private String semester;
    @SerializedName("Grading")
    @Expose
    private Integer grading;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Integer getGrading() {
        return grading;
    }

    public void setGrading(Integer grading) {
        this.grading = grading;
    }

}
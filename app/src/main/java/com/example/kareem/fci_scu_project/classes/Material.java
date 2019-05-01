package com.example.kareem.fci_scu_project.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Material {

    @SerializedName("Material_Id")
    @Expose
    private Integer materialId;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Size")
    @Expose
    private String size;
    @SerializedName("AddedOn")
    @Expose
    private String addedOn;
    @SerializedName("Subject_Id")
    @Expose
    private Integer subjectId;

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
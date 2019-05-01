package com.example.kareem.fci_scu_project.model;

/**
 * Created by youssef on 7/2/2019.
 */

public class ChildItem {

    private String parentId;
    private String childId;
    private String childName;
    private String isChecked;

    public ChildItem(String parentId, String childId, String childName, String isChecked) {
        this.parentId = parentId;
        this.childId = childId;
        this.childName = childName;
        this.isChecked = isChecked;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }
}

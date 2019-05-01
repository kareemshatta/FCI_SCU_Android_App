package com.example.kareem.fci_scu_project.model;

import java.util.List;

/**
 * Created by youssef on 7/2/2019.
 */

public class ParentItem {

    private String parentId;
    private String parentName;
    private String isChecked = "NO";
    private List<ChildItem> childItems;


    public ParentItem(String parentId, String parentName, String isChecked, List<ChildItem> childItems) {
        this.parentId = parentId;
        this.parentName = parentName;
        this.isChecked = isChecked;
        this.childItems = childItems;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public List<ChildItem> getChildItems() {
        return childItems;
    }

    public void setChildItems(List<ChildItem> childItems) {
        this.childItems = childItems;
    }
}
package com.example.kareem.fci_scu_project.classes;

public class StudentRule {

    boolean isSelected;
    String userName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;


    //now create constructor and getter setter method using shortcut like command+n for mac & Alt+Insert for window.


    public StudentRule(boolean isSelected, String userName,String id) {
        this.isSelected = isSelected;
        this.userName = userName;
        this.id = id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}


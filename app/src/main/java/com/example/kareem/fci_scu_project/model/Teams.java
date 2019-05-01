package com.example.kareem.fci_scu_project.model;

/**
 * Created by youssef on 13/2/2019.
 */

public class Teams {
    int id ;
    String Name;

    public Teams(int id, String name) {
        this.id = id;
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

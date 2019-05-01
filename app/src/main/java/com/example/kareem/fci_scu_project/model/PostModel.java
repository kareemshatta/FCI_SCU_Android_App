package com.example.kareem.fci_scu_project.model;

import java.util.ArrayList;

/**
 * Created by youssef on 1/2/2019.
 */

public class PostModel {
    int id, likes;
    String name, time,post;
    ArrayList<String> comments;

    public PostModel(int id, int likes, ArrayList<String> comments, String name, String time, String post) {
        this.id = id;
        this.likes = likes;
        this.comments = comments;
        this.post = post;
        this.name = name;
        this.time = time;

    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getId() {
        return id;
    }

    public int getLikes() {
        return likes;
    }



    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }
}

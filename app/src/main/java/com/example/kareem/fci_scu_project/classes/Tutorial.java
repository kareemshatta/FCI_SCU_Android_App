package com.example.kareem.fci_scu_project.classes;

public class Tutorial {

    int tutorialId;
    String tutorialName;

    public Tutorial(String tutorialName) {
        this.tutorialName = tutorialName;
    }

    public String getTutorialName() {
        return tutorialName;
    }

    public void setTutorialName(String tutorialName) {
        this.tutorialName = tutorialName;
    }

    public int getTutorialId() {
        return tutorialId;
    }

    public void setTutorialId(int tutorialId) {
        this.tutorialId = tutorialId;
    }
}

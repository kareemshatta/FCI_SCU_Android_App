package com.example.kareem.fci_scu_project.classes;

public class Quiz {
    int quizId;
    String quizName;

    public Quiz(String quizName) {
        this.quizName = quizName;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }
}

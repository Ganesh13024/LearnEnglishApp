package com.example.learnenglish.Models;

public class PracticeQuizContentModel {

    String title;
    String type;

    public PracticeQuizContentModel() {
    }

    public PracticeQuizContentModel(String title, String type) {
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

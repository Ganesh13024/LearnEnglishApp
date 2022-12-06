package com.example.learnenglish.Models;

public class ScoreModel {

    String type;
    String Score;

    String documentId;

    public ScoreModel() {
    }

    public ScoreModel(String type, String score) {
        this.type = type;
        Score = score;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }
}

package com.example.learnenglish.Models;

public class FavouriteStoryModel {

    String title;
    String type;
    String story1,story2,story3,story4,story5;
    String documentId;

    public FavouriteStoryModel() {
    }

    public FavouriteStoryModel(String title, String type, String story1, String story2, String story3, String story4, String story5) {
        this.title = title;
        this.type = type;
        this.story1 = story1;
        this.story2 = story2;
        this.story3 = story3;
        this.story4 = story4;
        this.story5 = story5;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
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

    public String getStory1() {
        return story1;
    }

    public void setStory1(String story1) {
        this.story1 = story1;
    }

    public String getStory2() {
        return story2;
    }

    public void setStory2(String story2) {
        this.story2 = story2;
    }

    public String getStory3() {
        return story3;
    }

    public void setStory3(String story3) {
        this.story3 = story3;
    }

    public String getStory4() {
        return story4;
    }

    public void setStory4(String story4) {
        this.story4 = story4;
    }

    public String getStory5() {
        return story5;
    }

    public void setStory5(String story5) {
        this.story5 = story5;
    }
}

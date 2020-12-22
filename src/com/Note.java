package com;

public class Note {
    private String text;
    private String userId;

    public Note() {
    }

    public Note(String text, String userId) {
        this.text = text;
        this.userId = userId;
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

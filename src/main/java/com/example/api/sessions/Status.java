package com.example.api.sessions;
public enum Status {

    EMPTY ("EMPTY"),
    SELECTION ("SELECTION"),
    GAME ("GAME");

    private final String title;

    Status(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

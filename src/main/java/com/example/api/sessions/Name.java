package com.example.api.sessions;

import java.util.List;

public enum Name {

    DARK ("DARK"),
    WHITE ("WHITE"),
    BLUE ("BLUE");

    private final String title;

    Name(String title) {
        this.title = title;
    }

    public static List<String> getAll() {
        return List.of(DARK.title, WHITE.title, BLUE.title);
    }

    public String getTitle() {
        return title;
    }
}

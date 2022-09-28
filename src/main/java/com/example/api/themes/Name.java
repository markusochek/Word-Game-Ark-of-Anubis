package com.example.api.themes;

public enum Name {

    ANIMALS ("животные"),
    VEHICLES ("транспортные средства"),
    INSTRUMENTS ("музыкальные инструменты"),
    WEAPONS ("холодное оружие");


    private final String title;

    Name(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

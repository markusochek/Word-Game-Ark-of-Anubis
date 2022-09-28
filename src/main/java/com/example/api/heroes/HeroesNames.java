package com.example.api.heroes;

public enum HeroesNames {
    MEDUSA ("MEDUSA"),
    HECATONCHIRES ("HECATONCHIRES"),
    HYDRA ("HYDRA"),
    PEGASUS ("PEGASUS");

    private final String title;

    HeroesNames(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

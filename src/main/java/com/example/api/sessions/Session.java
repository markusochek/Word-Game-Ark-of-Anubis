package com.example.api.sessions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private int numberOfPlayers;
    private String status;

    public Session() {
        this.name = "";
        this.numberOfPlayers = 0;
        this.status = Status.EMPTY.getTitle();
    }

    public Session(String name) {
        this.name = name;
        this.status = Status.EMPTY.getTitle();
    }
}

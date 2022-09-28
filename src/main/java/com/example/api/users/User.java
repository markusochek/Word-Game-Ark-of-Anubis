package com.example.api.users;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String login;
    @Column(unique = true)
    private String name;
    private String password;
    private String token;

    public User(String login, String name, String password, String token) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.token = token;
    }
}

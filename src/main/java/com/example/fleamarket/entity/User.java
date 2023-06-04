package com.example.fleamarket.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "User")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userIdx;

    @Column(name = "username")
    private String userName;

    @Column
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}

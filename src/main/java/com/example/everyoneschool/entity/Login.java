package com.example.everyoneschool.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "User")
@Data
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String userName;

    @Column
    private String password;
}

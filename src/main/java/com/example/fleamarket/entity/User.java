package com.example.fleemarket.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "User")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userIdx;

    @Column(name = "username")
    private String userName;

    @Column
    private String password;
}

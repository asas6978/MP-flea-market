package com.example.everyoneschool.repository;

public interface LoginCustomRepository {
    Long findUserId(String userName, String password);
}

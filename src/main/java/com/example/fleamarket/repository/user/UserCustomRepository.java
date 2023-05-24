package com.example.fleemarket.repository.user;

public interface UserCustomRepository {
    Long findUserIdx(String userName, String password);
}

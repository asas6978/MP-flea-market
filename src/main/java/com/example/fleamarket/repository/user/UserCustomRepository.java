package com.example.fleamarket.repository.user;

public interface UserCustomRepository {
    Long findUserIdx(String userName, String password);
}

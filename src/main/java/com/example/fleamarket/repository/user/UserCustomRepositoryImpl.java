package com.example.fleamarket.repository.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Long findUserIdx(String userName, String password) {
        String query = "select userIdx from User where userName = :userName and password = :password";
        return (Long) entityManager.createQuery(query)
                .setParameter("userName", userName)
                .setParameter("password", password)
                .getSingleResult();
    }
}

package com.example.everyoneschool.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class LoginCustomRepositoryImpl implements LoginCustomRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Long findUserId(String userName, String password) {
        String query = "select userId from User where userName = :userName and password = :password";
        return (Long) entityManager.createQuery(query)
                .setParameter("userName", userName)
                .setParameter("password", password)
                .getSingleResult();
    }
}

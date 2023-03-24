package com.example.everyoneschool.repository;

import com.example.everyoneschool.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoginRepository extends JpaRepository<Login, Long>, LoginCustomRepository {
}

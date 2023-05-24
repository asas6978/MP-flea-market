package com.example.fleemarket.repository.user;

import com.example.fleemarket.entity.User;
import com.example.fleemarket.repository.user.UserCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
}

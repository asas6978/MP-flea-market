package com.example.fleamarket.repository.user;

import com.example.fleamarket.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
    User findUserByUserNameAndPassword(String userName, String password);
}

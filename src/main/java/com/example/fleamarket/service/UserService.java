package com.example.fleemarket.service;

import com.example.fleemarket.constant.UserConst;
import com.example.fleemarket.dto.user.UserDto;
import com.example.fleemarket.dto.user.UserResponseDto;
import com.example.fleemarket.entity.User;
import com.example.fleemarket.exception.UserException;
import com.example.fleemarket.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto login(UserDto login) {
        try {
            Long userId = userRepository.findUserIdx(login.getUserName(), login.getPassword());
            return new UserResponseDto(userId, UserConst.LOGIN_SUCCESS);
        } catch (EmptyResultDataAccessException e) {
            log.info("LoginService.login.fail");
            throw new UserException(UserConst.INVALID_ID);
        }
    }

    public User getUserInfo(Long userIdx) {
        try {
            Optional<User> user = userRepository.findById(userIdx);
            if (user.isEmpty()) {
                throw new UserException();
            }
            return user.get();
        } catch (Exception e) {
            log.info("LoginService.getUserInfo.fail");
            throw new UserException(UserConst.INVALID_ID);
        }
    }
}

package com.example.fleamarket.service;

import com.example.fleamarket.constant.UserConst;
import com.example.fleamarket.dto.user.UserDto;
import com.example.fleamarket.dto.user.UserResponseDto;
import com.example.fleamarket.entity.User;
import com.example.fleamarket.exception.UserException;
import com.example.fleamarket.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto signup(UserDto userDto) {
        try {
            User saveUser = userRepository.save(new User(userDto.getUserName(), userDto.getPassword()));
            return new UserResponseDto(saveUser.getUserIdx(), UserConst.SIGNUP_SUCCESS);
        } catch (Exception e) {
            throw new UserException(UserConst.SIGNUP_FAIL);
        }
    }

    public UserResponseDto login(UserDto login) {
        try {
            Long userId = userRepository.findUserByUserNameAndPassword(login.getUserName(), login.getPassword()).getUserIdx();
            return new UserResponseDto(userId, UserConst.LOGIN_SUCCESS);
        } catch (Exception e) {
            log.info("LoginService.login.fail");
            throw new UserException(UserConst.LOGIN_FAIL);
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

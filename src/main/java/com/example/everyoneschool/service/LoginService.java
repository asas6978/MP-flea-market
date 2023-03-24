package com.example.everyoneschool.service;

import com.example.everyoneschool.dto.LoginDto;
import com.example.everyoneschool.dto.LoginResponseDto;
import com.example.everyoneschool.exception.LoginException;
import com.example.everyoneschool.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {
    private final LoginRepository loginRepository;

    public LoginResponseDto login(LoginDto login) {
        Long userId = loginRepository.findUserId(login.getUserName(), login.getPassword());

        if (userId == null) {
            log.info("LoginService.login.fail");
            throw new LoginException("Invalid Id");
        }

        return new LoginResponseDto(userId, "success");
    }
}

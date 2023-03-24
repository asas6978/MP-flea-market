package com.example.everyoneschool.service;

import com.example.everyoneschool.dto.LoginDto;
import com.example.everyoneschool.dto.LoginResponseDto;
import com.example.everyoneschool.exception.LoginException;
import com.example.everyoneschool.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {
    private final LoginRepository loginRepository;

    public LoginResponseDto login(LoginDto login) {
        try {
            Long userId = loginRepository.findUserId(login.getUserName(), login.getPassword());
            return new LoginResponseDto(userId, "success");
        } catch (EmptyResultDataAccessException e) {
            log.info("LoginService.login.fail");
            throw new LoginException("Invalid Id");
        }
    }
}

package com.example.everyoneschool.controller;

import com.example.everyoneschool.constant.SessionConst;
import com.example.everyoneschool.dto.LoginDto;
import com.example.everyoneschool.dto.LoginResponseDto;
import com.example.everyoneschool.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final LoginService loginService;

    @ResponseBody
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        try {
            LoginResponseDto user = loginService.login(loginDto);
            HttpSession session = request.getSession();
            session.setAttribute(SessionConst.LOGIN_USER, user);
            return user;
        } catch (Exception e) {
            log.info("", e);
            return new LoginResponseDto(null, e.getMessage());
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return SessionConst.LOGOUT_SUCCESS;
    }
}

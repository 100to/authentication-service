package com.example.authentication.controller;

import com.example.authentication.common.exception.InvalidPasswordException;
import com.example.authentication.common.exception.InvalidUserException;
import com.example.authentication.payload.request.LoginRequest;
import com.example.authentication.payload.response.LoginResponse;
import com.example.authentication.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest)
            throws InvalidPasswordException, InvalidUserException {
        return this.userService.login(loginRequest);
    }
}

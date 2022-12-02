package com.example.authentication.controller;

import com.example.authentication.payload.request.RegistrationRequest;
import com.example.authentication.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class AuthorizationController {
    private final UserService userService;

    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void register(@RequestBody @Valid RegistrationRequest request){
        this.userService.register(request);
    }
}

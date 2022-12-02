package com.example.authentication.service;

import com.example.authentication.common.exception.InvalidPasswordException;
import com.example.authentication.common.exception.InvalidUserException;
import com.example.authentication.payload.request.LoginRequest;
import com.example.authentication.payload.request.RegistrationRequest;
import com.example.authentication.payload.response.LoginResponse;

public interface UserService {
    void register(RegistrationRequest request);

    LoginResponse login (LoginRequest request) throws InvalidUserException, InvalidPasswordException;
}

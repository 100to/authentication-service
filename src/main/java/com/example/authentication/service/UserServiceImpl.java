package com.example.authentication.service;

import com.example.authentication.common.exception.InvalidPasswordException;
import com.example.authentication.common.exception.InvalidUserException;
import com.example.authentication.kafka.Producer;
import com.example.authentication.mapper.Mapper;
import com.example.authentication.payload.request.LoginRequest;
import com.example.authentication.payload.request.RegistrationRequest;
import com.example.authentication.payload.response.LoginResponse;
import com.example.authentication.repository.UserRepository;
import com.example.authentication.security.JwtGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Mapper mapper;
    private final JwtGenerator jwtGenerator;
    private final Producer producer;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, Mapper mapper, JwtGenerator jwtGenerator, Producer producer) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.jwtGenerator = jwtGenerator;
        this.producer = producer;
    }

    @Override
    public void register(RegistrationRequest request) {
        var user = this.mapper.map(request);
        user.setPassword(this.passwordEncoder.encode(request.getPassword()));
        var savedUser = this.userRepository.save(user);

        this.producer.publish(this.mapper.map(request, savedUser.getId()));
    }

    @Override
    public LoginResponse login(LoginRequest request) throws InvalidUserException, InvalidPasswordException {

        var user = this.userRepository.findByUsername(request.getUsername());
        if (user == null) {
            throw new InvalidUserException();
        }

        if (!this.passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        return new LoginResponse(this.jwtGenerator.generateToken(user));
    }
}

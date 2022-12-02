package com.example.authentication.security;

import com.example.authentication.common.constant.AuthConstants;
import com.example.authentication.common.exception.InvalidUserException;
import com.example.authentication.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class Filter extends BasicAuthenticationFilter {
    private final JwtGenerator jwtGenerator;
    private final UserRepository userRepository;

    public Filter(AuthenticationManager authenticationManager, JwtGenerator jwtGenerator, UserRepository userRepository) {
        super(authenticationManager);
        this.jwtGenerator = jwtGenerator;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        var token = request.getHeader(AuthConstants.TOKEN_PREFIX);

        if (token == null) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = null;
        try {
            authentication = this.getAuthentication(token);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) throws InvalidUserException {
        var decodedToken = this.jwtGenerator.decodeToken(token);

        var user = this.userRepository
                .findById(Long.valueOf(decodedToken.getSubject()))
                .orElseThrow(InvalidUserException::new);

        return new UsernamePasswordAuthenticationToken(
                user,
                null,
                new ArrayList<>()
        );
    }
}

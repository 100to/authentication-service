package com.example.authentication.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.authentication.common.constant.AuthConstants;
import com.example.authentication.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Configuration
public class JwtGenerator {

    private final String jwtSecret;

    public JwtGenerator(@Value("${jwt.secret.key}") String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public String generateToken(User user) {
        return JWT.create()
                .withSubject(String.valueOf(user.getId()))
                .withExpiresAt(
                        new Date(
                                System.currentTimeMillis()
                                        + TimeUnit.DAYS.toMillis(AuthConstants.TOKEN_EXPIRATION_TIME)
                        )
                )
                .sign(Algorithm.HMAC512(jwtSecret));
    }

    public DecodedJWT decodeToken(String token) {
        var pureToken = token.replace(AuthConstants.TOKEN_PREFIX, "");

        return JWT.require(Algorithm.HMAC512(jwtSecret))
                .build()
                .verify(pureToken);
    }
}

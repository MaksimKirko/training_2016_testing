package com.github.maximkirko.testing.services.impl;

import org.springframework.stereotype.Service;

import com.github.maximkirko.testing.services.AuthenticationService;


@Service
public class AuthenticationServiceImpl implements
        AuthenticationService {

    @Override
    public boolean validateUserPassword(String username,
            String password) {
        // TODO DAO query
        return username.equals("user")
                && password.equals("password");
    }

}

package com.github.maximkirko.testing.services;

public interface AuthenticationService {

    boolean validateUserPassword(String username, String password);
}

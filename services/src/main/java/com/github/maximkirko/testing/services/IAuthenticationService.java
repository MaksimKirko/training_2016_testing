package com.github.maximkirko.testing.services;

public interface IAuthenticationService {

    boolean validateUserPassword(String username, String password);
    
    boolean validateUserRole(String username);
}

package com.in28minutes.springboot.myFirstWebApp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public boolean authentication(String username , String password){
        boolean isValidUserName = username.equalsIgnoreCase("biswajit");
        boolean isValidPassword = password.equalsIgnoreCase("sahoo");
        return isValidUserName && isValidPassword;
    }
}

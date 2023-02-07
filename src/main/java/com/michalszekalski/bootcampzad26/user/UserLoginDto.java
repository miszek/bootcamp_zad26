package com.michalszekalski.bootcampzad26.user;

import java.util.Set;

public class UserLoginDto {
    private final String email;
    private final String password;
    private final Set<String> roles;

    public UserLoginDto(String email, String password, Set<String> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getRoles() {
        return roles;
    }
}

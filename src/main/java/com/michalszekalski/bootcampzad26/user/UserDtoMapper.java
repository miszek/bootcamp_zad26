package com.michalszekalski.bootcampzad26.user;

import java.util.Set;
import java.util.stream.Collectors;

class UserDtoMapper {

    static UserLoginDto map(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        Set<String> roles = user.getRoles()
                .stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());
        return new UserLoginDto(email, password, roles);
    }
}

package com.michalszekalski.bootcamp_zad26.user;

import java.util.Set;
import java.util.stream.Collectors;

class UserDtoMapper {

    static UserDto map(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        Set<String> roles = user.getRoles()
                .stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());
        return new UserDto(email, password, roles);
    }
}

package com.michalszekalski.bootcampzad26.user;

import java.util.Set;
import java.util.stream.Collectors;

class UserRegistrationDtoMapper {

    static UserRegistrationDto map(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        Set<String> roles = user.getRoles()
                .stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());
        return new UserRegistrationDto(email, password, firstName, lastName, roles);
    }
}

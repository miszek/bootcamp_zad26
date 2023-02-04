package com.michalszekalski.bootcamp_zad26.user;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserDto> findCredentialsByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserDtoMapper::map);
    }
}

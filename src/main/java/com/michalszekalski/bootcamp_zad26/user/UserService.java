package com.michalszekalski.bootcamp_zad26.user;

import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private static final String USER_ROLE = "USER";
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserLoginDto> findCredentialsByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserDtoMapper::map);
    }

    public Optional<UserRegistrationDto> findRegistrationCredentialsByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserRegistrationDtoMapper::map);
    }

    @Transactional
    public void register(UserRegistrationDto registration) {
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        String passwordHash = passwordEncoder.encode(registration.getPassword());
        user.setPassword(passwordHash);
        Optional<UserRole> userRole = userRoleRepository.findByName(USER_ROLE);
        userRole.ifPresentOrElse(
                role -> user.getRoles().add(role),
                () -> {
                    throw new NoSuchElementException();
                }
        );
        userRepository.save(user);
    }

    @Transactional
    public void changeCredentials(UserRegistrationDto newCredentials) {
        User currentUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
        if (!newCredentials.getFirstName().equals("")) {
            currentUser.setFirstName(newCredentials.getFirstName());
        }
        if (!newCredentials.getLastName().equals("")) {
            currentUser.setLastName(newCredentials.getLastName());
        }
        if (!newCredentials.getPassword().equals("")) {
            currentUser.setPassword(passwordEncoder.encode(newCredentials.getPassword()));
        }
        userRepository.save(currentUser);
    }

    public List<UserRegistrationDto> findAllUsersExceptCurrent() {
        return userRepository.findAllByEmailNot(SecurityContextHolder.getContext().getAuthentication().getName())
                .stream().map(UserRegistrationDtoMapper::map)
                .toList();
    }

    @Transactional
    public void addRemoveAdminRole(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s not found", email)));
        UserRole adminRole = userRoleRepository.findByName("ADMIN").orElseThrow(() -> new UsernameNotFoundException("Role not found"));
        UserRole userRole = userRoleRepository.findByName("USER").orElseThrow(() -> new UsernameNotFoundException("Role not found"));
        if(user.getRoles().contains(adminRole)) {
            user.getRoles().remove(adminRole);
        } else {
            user.getRoles().add(adminRole);
        }
        if (user.getRoles().isEmpty()) {
            user.getRoles().add(userRole);
        }
        userRepository.save(user);
    }
}

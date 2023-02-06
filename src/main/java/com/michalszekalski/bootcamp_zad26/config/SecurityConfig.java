package com.michalszekalski.bootcamp_zad26.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests
                (requests -> requests
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .requestMatchers("/img/**", "/styles/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/register", "/confirmation").permitAll()
                        .anyRequest().authenticated()

                );
        http.formLogin
                (login ->login
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/user",true));
        http.logout(logout -> logout
                .logoutSuccessUrl("/"));
        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}

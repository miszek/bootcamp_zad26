package com.michalszekalski.bootcamp_zad26.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class LoginController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    String loginForm() {
        return "login-form";
    }

}

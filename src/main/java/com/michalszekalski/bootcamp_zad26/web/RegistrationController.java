package com.michalszekalski.bootcamp_zad26.web;

import com.michalszekalski.bootcamp_zad26.user.UserRegistrationDto;
import com.michalszekalski.bootcamp_zad26.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    String registrationForm(Model model) {
        UserRegistrationDto user = new UserRegistrationDto();
        model.addAttribute("user", user);
        return "registration-form";
    }

    @PostMapping("/register")
    String register(UserRegistrationDto userRegistrationDto) {

        if (userService.findCredentialsByEmail(userRegistrationDto.getEmail()).isEmpty()) {
            userService.register(userRegistrationDto);
            return "redirect:/confirmation";
        } else {
            return "registrationFail";
        }
    }

    @GetMapping("/confirmation")
    String registrationConfirmation() {
        return "registration-confirmation";
    }

    @GetMapping("/user/userCredentials")
    String userCredentialsForm(Authentication authentication, Model model) {
        UserRegistrationDto actualCredentials = userService.findRegistrationCredentialsByEmail(authentication.getName()).orElseThrow();
        model.addAttribute("actualCredentials", actualCredentials);
        model.addAttribute("user", new UserRegistrationDto());

        return "changeCredentialsForm";
    }

    @PostMapping("/user/changeCredentials")
    String changeCredentials(UserRegistrationDto userRegistrationDto) {
        if (userRegistrationDto.getFirstName().equals("") && userRegistrationDto.getLastName().equals("")
                && userRegistrationDto.getPassword().equals("")) {
            return "credentialsNotChanged";
        }
        userService.changeCredentials(userRegistrationDto);
        return "credentialsChangeConfirmation";
    }
}

package com.example.airlines.controller;

import com.example.airlines.model.User;
import com.example.airlines.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        // Spróbuj zarejestrować użytkownika
        if (userService.registerUser(user)) {
            return "redirect:/login"; // Przekieruj do strony logowania po udanej rejestracji
        } else {
            model.addAttribute("error", "Username already exists"); // Dodaj komunikat o błędzie
            return "register"; // Wróć do formularza rejestracji
        }
    }
}
package ru.valinkin.meowapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.valinkin.meowapp.models.User;
import ru.valinkin.meowapp.services.UserService;

import java.util.Optional;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/auth")
    public String authPage(Model model) {
        model.addAttribute("user", new User());
        return "auth";
    }

    @PostMapping("/auth")
    public String login(@ModelAttribute("user") User formUser, Model model, RedirectAttributes redirectAttributes) {
        Optional<User> found = userService.findByLogin(formUser.getLogin());

        if (found.isPresent() && found.get().getPassword().equals(formUser.getPassword())) {
            redirectAttributes.addFlashAttribute("user", found.get());
            return "redirect:/chats";
        } else {
            model.addAttribute("error", "Неверный логин или пароль");
            return "auth";
        }
    }
}
package ru.valinkin.meowapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller1 {
    @GetMapping("/auth")
    public String Auth() {
        return "auth";
    }
}

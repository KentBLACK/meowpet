package ru.valinkin.meowapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.valinkin.meowapp.models.User;
import ru.valinkin.meowapp.security.PersonDetails;
import ru.valinkin.meowapp.services.ChatService;
import ru.valinkin.meowapp.services.UserDetailsService;

@Controller
public class ChatController {
    private final ChatService chatService;

    ChatController(ChatService chatService){
        this.chatService = chatService;
    }
    @GetMapping("/chats")
    public String chatsPage(@ModelAttribute ("user") User user, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        model.addAttribute("user", personDetails.getUser());
        model.addAttribute("chats", chatService.getChats(personDetails.getUser().getID()));
        return "chats";
    }
}

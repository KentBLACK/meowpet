package ru.valinkin.meowapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.valinkin.meowapp.models.User;
import ru.valinkin.meowapp.services.ChatService;

@Controller
public class ChatController {
    private final ChatService chatService;

    ChatController(ChatService chatService){
        this.chatService = chatService;
    }
    @GetMapping("/chats")
    public String chatsPage(@ModelAttribute ("user") User user, Model model) {
        if (user.getLogin() == null){
            return "redirect:/auth";
        }
        model.addAttribute("user", user);
        model.addAttribute("chats", chatService.getChats(user.getID()));
        return "chats";
    }
}

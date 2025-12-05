package ru.valinkin.meowapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.valinkin.meowapp.security.PersonDetails;
import ru.valinkin.meowapp.services.ChatService;
import ru.valinkin.meowapp.services.MessageService;
import ru.valinkin.meowapp.services.UserService;

import java.util.Collections;

@Controller
public class ChatController {
    private final MessageService messageService;
    private final ChatService chatService;
    private final UserService userService;

    @Autowired
    public ChatController(MessageService messageService, ChatService chatService, UserService userService) {
        this.messageService = messageService;
        this.chatService = chatService;
        this.userService = userService;
    }

    @GetMapping("/chats")
    public String ClearChat(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) auth.getPrincipal();


        model.addAttribute("messages", Collections.emptyList());
        model.addAttribute("chatId", 0);
        model.addAttribute("currentUserId", personDetails.getUser().getID());
        model.addAttribute("activeChats", chatService.getChats(personDetails.getUser().getID()));

        return "chats_empty";
    }

    @GetMapping("/chats/{chatId}")
    public String currentChat(@PathVariable int chatId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) auth.getPrincipal();

        model.addAttribute("messages", messageService.getMessages(chatId));
        model.addAttribute("chatId", chatId);
        model.addAttribute("currentUserId", personDetails.getUser().getID());
        model.addAttribute("activeChats", chatService.getChats(personDetails.getUser().getID()));

        return "chats";
    }

    @PostMapping("/chats/{chatId}/send")
    public String sendMessage(@PathVariable int chatId,
                              @RequestParam String text) {
        messageService.save(chatId, text);
        return "redirect:/chats/" + chatId;
    }
}
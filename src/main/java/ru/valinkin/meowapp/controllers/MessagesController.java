package ru.valinkin.meowapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.valinkin.meowapp.models.Chat;
import ru.valinkin.meowapp.security.PersonDetails;
import ru.valinkin.meowapp.services.ChatService;
import ru.valinkin.meowapp.services.MessageService;

import java.util.List;

@Controller
public class MessagesController {
    private final MessageService messageService;
    private final ChatService chatService;

    public MessagesController(MessageService messageService, ChatService chatService) {
        this.messageService = messageService;
        this.chatService = chatService;
    }

    @GetMapping("/chats/{chatId}")
    public String currentChat(@PathVariable int chatId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        if (chatService.getChats(chatId).stream().filter(chat -> chat.getId() == personDetails.getUser().getID()).toList().isEmpty()){
            return "redirect:/chats";
        }


        model.addAttribute("messages", messageService.getMessages(chatId));
        model.addAttribute("chatId", chatId);
        return "currentChat";
    }

    @PostMapping("/chats/{chatId}/send")
    public String sendMessage(@PathVariable int chatId,
                              @RequestParam String text) {
        messageService.save(chatId, text);
        return "redirect:/chats/" + chatId;
    }
}
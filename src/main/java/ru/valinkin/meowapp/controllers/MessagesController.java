package ru.valinkin.meowapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.valinkin.meowapp.services.MessageService;

@Controller
public class MessagesController {
    private final MessageService messageService;

    public MessagesController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/chats/{chatId}")
    public String currentChat(@PathVariable int chatId, Model model) {
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
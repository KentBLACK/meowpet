package ru.valinkin.meowapp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.valinkin.meowapp.models.Chat;
import ru.valinkin.meowapp.models.Message;
import ru.valinkin.meowapp.models.User;
import ru.valinkin.meowapp.repositories.ChatRepository;
import ru.valinkin.meowapp.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ChatService {
    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository){
        this.chatRepository = chatRepository;
    }

    public List<Chat> getChats(int chatID){
        List<Chat> list1 = chatRepository.findAllByUser1id(chatID);
        List<Chat> list2 = chatRepository.findAllByUser2id(chatID);
        list1.addAll(list2);
        return list1;
    }

    @Transactional
    public void save(Chat chat) {
        chatRepository.save(chat);
    }
}

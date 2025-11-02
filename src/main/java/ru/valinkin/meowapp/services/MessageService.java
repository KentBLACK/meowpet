package ru.valinkin.meowapp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.valinkin.meowapp.models.Chat;
import ru.valinkin.meowapp.models.Message;
import ru.valinkin.meowapp.repositories.ChatRepository;
import ru.valinkin.meowapp.repositories.MessageRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;

    public MessageService(MessageRepository messageRepository,  ChatRepository chatRepository){
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
    }

    public List<Message> getMessages(int chatId){
        return messageRepository.findAllByChatId(chatId);
    }

    @Transactional
    public void save(int chatId, String text) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Чат не найден с ID: " + chatId));

        Message message = new Message();
        message.setText(text);
        message.setChat(chat);

        messageRepository.save(message);
    }
}

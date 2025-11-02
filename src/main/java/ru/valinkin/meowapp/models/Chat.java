package ru.valinkin.meowapp.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user1id")
    private int user1id;

    @Column(name = "user2id")
    private int user2id;

    // Опционально: связь с сообщениями
    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY)
    private List<Message> messages = new ArrayList<>();

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUser1id() { return user1id; }
    public void setUser1id(int user1id) { this.user1id = user1id; }

    public int getUser2id() { return user2id; }
    public void setUser2id(int user2id) { this.user2id = user2id; }

    public List<Message> getMessages() { return messages; }
    public void setMessages(List<Message> messages) { this.messages = messages; }
}

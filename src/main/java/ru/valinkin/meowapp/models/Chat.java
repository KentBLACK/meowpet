package ru.valinkin.meowapp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "chats")
public class Chat {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user1id")
    private int user1id;

    @Column(name = "user2id")
    private int user2id;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUser1id() {
        return user1id;
    }
    public void setUser1id(int user1id) {
        this.user1id = user1id;
    }
    public int getUser2id() {
        return user2id;
    }
    public void setUser2id(int user2id) {
        this.user2id = user2id;
    }
}

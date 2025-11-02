package ru.valinkin.meowapp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String login;

    @Column(name = "password")
    private String password;

    public User(){}

    public User(String login, String password){
        this.login = login;
        this.password = password;
    }

    public int getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

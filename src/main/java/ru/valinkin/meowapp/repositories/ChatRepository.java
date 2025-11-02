package ru.valinkin.meowapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.valinkin.meowapp.models.Chat;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    List<Chat> findAllByUser1id(int id);
    List<Chat> findAllByUser2id(int id);
}
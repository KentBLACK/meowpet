package ru.valinkin.meowapp.repositories;

import ru.valinkin.meowapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

public class AuthRepository {
    @Repository
    public interface UserRepository extends JpaRepository<User, Long> {

        // Spring Data JPA автоматически реализует этот метод!
        Optional<User> findByUsername(String username);

        // Проверка существования пользователя
        boolean existsByUsername(String username);
    }
}

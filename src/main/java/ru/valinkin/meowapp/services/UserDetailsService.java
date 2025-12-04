package ru.valinkin.meowapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.valinkin.meowapp.models.User;
import ru.valinkin.meowapp.repositories.UserRepository;
import ru.valinkin.meowapp.security.PersonDetails;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository  userRepository;

    @Autowired
    public UserDetailsService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        else{
            return new PersonDetails(user.get());
        }
    }
}

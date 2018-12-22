package se.ifmo.ru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ifmo.ru.domain.dao.UserRepository;
import se.ifmo.ru.domain.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImlp implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImlp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> loadById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<List<User>> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users :: add);
        return Optional.of(users);
    }
}

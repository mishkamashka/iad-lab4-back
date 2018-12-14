package se.ifmo.ru.service;

import org.springframework.stereotype.Service;
import se.ifmo.ru.domain.model.User;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    Optional<User> loadById(Long id);

    Optional<List<User>> getAllPoints();
}

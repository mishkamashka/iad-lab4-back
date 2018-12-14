package se.ifmo.ru.domain.dao;

import org.springframework.data.repository.CrudRepository;
import se.ifmo.ru.domain.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}

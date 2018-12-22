package se.ifmo.ru.domain.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.ifmo.ru.domain.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}

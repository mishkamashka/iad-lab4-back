package se.ifmo.ru.domain.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.ifmo.ru.domain.model.Point;
import se.ifmo.ru.domain.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface PointRepository extends CrudRepository<Point, Long> {

}

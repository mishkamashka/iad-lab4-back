package se.ifmo.ru.domain.dao;

import org.springframework.data.repository.CrudRepository;
import se.ifmo.ru.domain.model.Point;

public interface PointRepository extends CrudRepository<Point, Long> {
}

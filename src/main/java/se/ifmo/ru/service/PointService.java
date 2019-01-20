package se.ifmo.ru.service;

import org.springframework.stereotype.Service;
import se.ifmo.ru.domain.model.Point;
import se.ifmo.ru.domain.model.User;

import java.util.List;
import java.util.Optional;

@Service
public interface PointService {

    Optional<Point> loadById(Long id);

    Optional<List<Point>> getAllPoints();

    Point save(Point point);

    void delete(Point point);

    void deleteAll();

}

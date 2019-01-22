package se.ifmo.ru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ifmo.ru.domain.dao.PointRepository;
import se.ifmo.ru.domain.model.Point;
import se.ifmo.ru.domain.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;

    @Autowired
    public PointServiceImpl(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public Optional<Point> loadById(Long id) {
        return pointRepository.findById(id);
    }

    public Optional<List<Point>> getAllPoints() {
        List<Point> points = new ArrayList<>();
        pointRepository.findAll().forEach(points::add);
        return Optional.of(points);
    }

    @Override
    public void save(Point point) {
         pointRepository.save(point);
    }

    @Override
    public void delete(Point point) {
        pointRepository.delete(point);
    }

    @Override
    public void updatePointsRadius(double radius) {
        List<Point> points = new ArrayList<>();
        pointRepository.findAll().forEach(points::add);
        for (Point point : points) {
            point.setR(radius);
            point.setInArea(point.checkIsInArea());
            pointRepository.save(point);
        }
    }

    @Override
    public void deleteAll() {
        pointRepository.deleteAll();
    }
}

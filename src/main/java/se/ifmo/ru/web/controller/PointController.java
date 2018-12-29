package se.ifmo.ru.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import se.ifmo.ru.domain.model.Point;
import se.ifmo.ru.domain.model.User;
import se.ifmo.ru.service.PointService;
import se.ifmo.ru.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/points")
public class PointController {

    private final PointService pointService;
    private final UserService userService;

    @Autowired
    public PointController(PointService pointService, UserService userService) {
        this.pointService = pointService;
        this.userService = userService;
    }

    @GetMapping("/list/{user_id}")
    public List<Point> getAllPoints(@PathVariable("user_id") Long userId) {
        User user;
        List<Point> points;
        Optional<User> optionalUser = userService.loadById(userId);
        if (optionalUser.isPresent())
            user = optionalUser.get();
        else {
            System.err.println("Not present user id");
            return null;
        }
        Optional<List<Point>> optionalPoints = pointService.getPointsByUser(user);
        points = optionalPoints.orElseGet(ArrayList::new);
        return points;
    }

    @GetMapping("/new_point_{x}_{y}_{r}_{user_id}")
    public Point save(@PathVariable("x") Double x, @PathVariable("y") Double y,
                      @PathVariable("r") Double r, @PathVariable("user_id") Long userId) {
        Optional<User> optionalUser = userService.loadById(userId);
        User user = optionalUser.isPresent() ? optionalUser.get() : null;
        return pointService.save(new Point(x, y, r, user));
    }

    @GetMapping("/delete_{point_id}")
    public void delete(@PathVariable("point_id") Long pointId) {
        Optional<Point> optionalPoint = pointService.loadById(pointId);
        Point point = optionalPoint.isPresent() ? optionalPoint.get() : null;
        pointService.delete(point);
    }

    @GetMapping("/list/{user_id}/clear")
    public List<Point> clearList(@PathVariable("user_id") Long userId) {
        List<Point> points;
        Optional<User> optionalUser = userService.loadById(userId);
        User user = optionalUser.isPresent() ? optionalUser.get() : null;
        Optional<List<Point>> optionalPoints = pointService.getPointsByUser(user);
        points = optionalPoints.orElseGet(ArrayList::new);
        points.forEach(pointService::delete);
        return points;
    }
}

package se.ifmo.ru.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.ifmo.ru.domain.model.Point;
import se.ifmo.ru.domain.model.User;
import se.ifmo.ru.service.PointService;
import se.ifmo.ru.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/points")
public class PointController {

    private final PointService pointService;

    @Autowired
    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @CrossOrigin
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Point>> getAllPoints() {
//        TODO: should i check current user profile?
        return pointService.getAllPoints().map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
    public ResponseEntity<Void> deleteAllPoints() {
        pointService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping("/new_point_{x}_{y}_{r}_{user_id}")
//    public Point save(@PathVariable("x") Double x, @PathVariable("y") Double y,
//                      @PathVariable("r") Double r, @PathVariable("user_id") Long userId) {
//        Optional<User> optionalUser = userService.loadById(userId);
//        User user = optionalUser.isPresent() ? optionalUser.get() : null;
//        return pointService.save(new Point(x, y, r, user));
//    }

    @GetMapping("/delete_{point_id}")
    public void delete(@PathVariable("point_id") Long pointId) {
        Optional<Point> optionalPoint = pointService.loadById(pointId);
        Point point = optionalPoint.isPresent() ? optionalPoint.get() : null;
        pointService.delete(point);
    }

//    @GetMapping("/list/{user_id}/clear")
//    public List<Point> clearList(@PathVariable("user_id") Long userId) {
//        List<Point> points;
//        Optional<User> optionalUser = userService.loadById(userId);
//        User user = optionalUser.isPresent() ? optionalUser.get() : null;
//        Optional<List<Point>> optionalPoints = pointService.getPointsByUser(user);
//        points = optionalPoints.orElseGet(ArrayList::new);
//        points.forEach(pointService::delete);
//        return points;
//    }
}

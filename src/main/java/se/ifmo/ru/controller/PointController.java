package se.ifmo.ru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
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

    @CrossOrigin
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addPoint(@RequestBody Point newPoint, UriComponentsBuilder uriComponentsBuilder) {
        Point point = new Point(newPoint.getX(), newPoint.getY(), newPoint.getR());
        pointService.save(point);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/api/feedback/{id}").buildAndExpand(point.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Point> getPointById(@PathVariable Long id) {
        return pointService.loadById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @RequestMapping(value = "/{radius}", method = RequestMethod.PUT)
    public  ResponseEntity<Void> updatePointsRadius(@PathVariable double radius) {
        pointService.updatePointsRadius(radius);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//    @GetMapping("/delete_{point_id}")
//    public void delete(@PathVariable("point_id") Long pointId) {
//        Optional<Point> optionalPoint = pointService.loadById(pointId);
//        Point point = optionalPoint.isPresent() ? optionalPoint.get() : null;
//        pointService.delete(point);
//    }

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

package se.ifmo.ru.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import se.ifmo.ru.domain.dao.PointRepository;
import se.ifmo.ru.service.PointService;

@Controller
public class PointController {

    @Autowired
    PointService pointService;

}

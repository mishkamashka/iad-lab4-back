package se.ifmo.ru.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.ifmo.ru.domain.model.User;
import se.ifmo.ru.service.PointService;
import se.ifmo.ru.service.UserService;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final PointService pointService;

    @Autowired
    RegistrationController(UserService userService, PointService pointService) {
        this.pointService = pointService;
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String login() { return "login"; }

    @RequestMapping("/logout")
    public String logout() {
        //this.role = anonymous;
        return "index";
    }

    @RequestMapping("/registration")
    public String registration() {return "registration"; }

    @PostMapping("/register_new_user")
    public User registerNewUser() {

        String login = "";
        String password = "";
        //TODO: retrieve login and encoded password form post request

        return userService.save(new User(login, password));
    }
}

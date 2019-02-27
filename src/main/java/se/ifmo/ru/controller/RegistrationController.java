package se.ifmo.ru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.ifmo.ru.domain.model.User;
import se.ifmo.ru.service.PointService;
import se.ifmo.ru.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final PointService pointService;

    @Autowired
    RegistrationController(UserService userService, PointService pointService) {
        this.pointService = pointService;
        this.userService = userService;
    }

//    @PreAuthorize("hasRole('ROLE_OTHER')")
//    @RequestMapping("/login")
//    public ResponseEntity<String> login() { return new ResponseEntity<>("login", HttpStatus.OK); }

    @RequestMapping("/check")
    public ResponseEntity<String> check() {return new ResponseEntity<>("registration", HttpStatus.OK); }

    @RequestMapping("/registration")
    public ResponseEntity<String> registration() {return new ResponseEntity<>("registration", HttpStatus.OK); }

    @PostMapping("/register_new_user")
    public User registerNewUser() {

        String login = "";
        String password = "";
        //TODO: retrieve login and encoded password form post request

        return userService.save(new User(login, password));
    }

    @CrossOrigin
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllPoints() {
//        TODO: should i check current user profile?
        return userService.getAllUsers().map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}

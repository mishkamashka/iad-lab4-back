package se.ifmo.ru.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationController {

    @RequestMapping("/login")
    public String login() { return "login"; }

    @RequestMapping("/registration")
    public String registration() {return "registration"; }
}

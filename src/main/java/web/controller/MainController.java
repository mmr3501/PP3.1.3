package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/user")
    public String printWelcome() {
        return "User";
    }

    @GetMapping("/admin")
    public String hello2() {
        return "Admin";
    }

}
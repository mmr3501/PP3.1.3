package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.service.UserService;



@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/user")
    public String printWelcome() {
        return "bootstrapUser";
    }

    @GetMapping("/admin")
    public String hello2() {
        return "bootstrapAdmin";
    }

}
package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.Arrays;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/")
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String printWelcome(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", userService.getUserByName(authentication.getName()));
        return "bootstrapUser";
    }

    @GetMapping("/admin")
    public String hello2(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", userService.getUserByName(authentication.getName()));
        model.addAttribute("newUser", new User());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("allRoles", userService.getAllRoles());
        return "bootstrapAdmin";
    }

    @PostMapping("/save")
    public String createUser(@RequestParam(value="ArrayOfRoles", required = false) String[] checked, User user) {
        if (checked != null) {
            user.setRoles(Arrays.stream(checked).map(a -> new Role(a)).collect(Collectors.toSet()));
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String deleteUser(long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public User findOne(Long id) {
        return userService.getUserById(id);
    }
}
package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String printWelcome(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", userService.loadUserByUsername(authentication.getName()));
        return "user";
    }

    @GetMapping("/admin")
    public String hello2(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/adduser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", userService.getAllRoles());
        return "adduser";
    }

    @PostMapping("/adduser")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("allRoles", userService.getAllRoles());
        return "edit";
    }

    @PostMapping("/edit")
    public String updateUser(User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.removeUserById(id);
        return "redirect:/admin";
    }
}
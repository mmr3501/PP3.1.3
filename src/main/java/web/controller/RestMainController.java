package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class RestMainController {


    private final UserService userService;

    @Autowired
    public RestMainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allRoles")
    public ResponseEntity<List<Role>> allRoles() {
        List <Role> allRoles = userService.getAllRoles();
        return new ResponseEntity<>(allRoles, HttpStatus.OK);
    }

    @GetMapping("/findMe")
    public ResponseEntity<List<User>> me() {
        List <User> list = new ArrayList<>();
        User me = userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        list.add(me);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<User> findOne(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<User>> findAll() {
        List <User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<User> createUser(@RequestBody  User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(userService.getUserByName(user.getUsername()), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.removeUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

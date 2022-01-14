package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import java.util.List;


@RestController
@RequestMapping("/api")
public class RestMainController {


    private final UserService userService;

    @Autowired
    public RestMainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findOne(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() {
        List <User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody  User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.removeUserById(id);
        return ResponseEntity.ok().build();
    }

}

package exercise.controller;

import exercise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import exercise.service.UserService;

import java.lang.management.MonitorInfo;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "")
    public Flux<User> getUsers() {
        return userService.findAll();
    }

    // BEGIN
    @PostMapping(path = "")
    public Mono<User> create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping(path = "/{id}")
    public Mono<User> getUser(@PathVariable int id) {
        return userService.findById(id);
    }

    @PatchMapping(path = "/{id}")
    public Mono<User> update(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        return userService.update(user);
    }

    @DeleteMapping(path = "/{id}")
    public Mono<Void> delete(@PathVariable int id) {
        return userService.delete(id);
    }
    // END
}

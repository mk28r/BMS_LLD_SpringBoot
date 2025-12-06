package org.example.bookmyshow_lld.controllers;

import org.example.bookmyshow_lld.models.User;
import org.example.bookmyshow_lld.services.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.createUser(user);
    }
}

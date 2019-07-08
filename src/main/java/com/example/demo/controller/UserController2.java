package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class UserController2 {

    private final UserService userService;

    @GetMapping("/user2/{user_id}")
    Mono<User> getUser(@PathVariable("user_id") final String userId) {
        return userService.getUser(userId);
    }

}

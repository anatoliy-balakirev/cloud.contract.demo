package com.example.demo.service;

import com.example.demo.model.User;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> getUser(final String userId);
}

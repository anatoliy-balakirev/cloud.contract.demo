package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public Mono<User> getUser(final String userId) {
        return Mono.just(createUser(userId));
    }

    User createUser(final String userId) {
        if ("user1".equals(userId)) {
            return User.builder()
                .firstName("User 1 first name")
                .lastName("User 1 last name")
                .build();
        } else {
            return User.builder()
                .firstName("Other user first name")
                .lastName("Other user last name")
                .build();
        }
    }
}

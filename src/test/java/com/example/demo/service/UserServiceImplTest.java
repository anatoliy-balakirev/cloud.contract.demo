package com.example.demo.service;

import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserServiceImpl.class})
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    void createUser1() {
        final User user1 = userService.createUser("user1");
        assertNotNull(user1);
        assertEquals("User 1 first name", user1.getFirstName());
        assertEquals("User 1 last name", user1.getLastName());

    }
}
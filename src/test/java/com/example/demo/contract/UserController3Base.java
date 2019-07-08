package com.example.demo.contract;


import com.example.demo.controller.UserController3;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static reactor.core.publisher.Mono.error;
import static reactor.core.publisher.Mono.just;

@WebFluxTest(UserController3.class)
@ContextConfiguration(classes = {UserController3.class, WebClientAutoConfiguration.class})
public class UserController3Base {

    @Autowired
    private WebTestClient webClient;
    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp() {
        RestAssuredWebTestClient.webTestClient(webClient);
        when(userService.getUser(eq("user1")))
            .thenReturn(just(User.builder().firstName("first name").lastName("last name").build()));
        when(userService.getUser(eq("user2")))
            .thenReturn(just(User.builder().firstName("first name 2").lastName("last name 2").build()));
        when(userService.getUser(eq("user3")))
            .thenReturn(error(new RuntimeException("Expected exception for tests")));
    }

}

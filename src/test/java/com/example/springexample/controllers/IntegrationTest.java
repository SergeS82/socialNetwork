package com.example.springexample.controllers;

import com.example.springexample.repository.AuthorRepository;
import com.example.springexample.repository.SubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension.class)
public class IntegrationTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private static final GenericContainer<?>
            pgContainer = new GenericContainer<>(DockerImageName.parse("sergs82/socialnetwork-db:latest"))
            .withExposedPorts(5432)
            .withEnv("POSTGRES_DB", "socialnetwork")
            .withEnv("POSTGRES_USER", "admin")
            .withEnv("POSTGRES_PASSWORD", "root");

    @BeforeEach
    void startContainer() {
        pgContainer.start();
        String host = pgContainer.getHost();
        Integer port = pgContainer.getMappedPort(5432);
        System.setProperty("spring.datasource.url", "jdbc:postgresql://" + host + ":" + port + "/socialnetwork");
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("Загрузка справочника Авторов.")
    void createSomeAuthors() {
    }
}

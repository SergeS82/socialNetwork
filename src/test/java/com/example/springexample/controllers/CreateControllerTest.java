package com.example.springexample.controllers;

import com.example.springexample.dto.AuthorDto;
import com.example.springexample.dto.SubscriptionDto;
import com.example.springexample.entity.Author;
import com.example.springexample.entity.Subscription;
import com.example.springexample.repository.AuthorRepository;
import com.example.springexample.repository.SubscriptionRepository;
import com.example.springexample.test.TestEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class CreateControllerTest {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    private void init() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("Загрузка справочника Авторов.")
    void createSomeAuthors() throws IOException, URISyntaxException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        TestEntity<Author, AuthorDto> testEntity = new TestEntity<>("/test_data/author_data.json", AuthorDto.class, authorRepository, webApplicationContext);
        testEntity.postAll("/author");
    }
    @Test
    @DisplayName("Загрузка справочника Подписок.")
    void createSomeSubscriptions() throws IOException, URISyntaxException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        TestEntity<Subscription, SubscriptionDto> testEntity = new TestEntity<>("/test_data/subscription_data.json", SubscriptionDto.class, subscriptionRepository, webApplicationContext);
        testEntity.postAll("/subscription");
    }

}
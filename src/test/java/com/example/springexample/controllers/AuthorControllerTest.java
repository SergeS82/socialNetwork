package com.example.springexample.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension.class)
class AuthorControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    private void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void create() throws Exception {
        String request = "{\"firstName\": \"Ivan\", \"lastName\": \"Ivanov\", \"mail\": \"test@ya.ru\", \"phone\": \"+79777777777\", \"sex\": \"M\", \"city\": \"Moscow\" }";
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/author")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request));
        resultActions.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
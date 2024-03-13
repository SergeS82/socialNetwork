package controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

class SubscriptionControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;



    @BeforeEach
    private void init() throws IOException {
        //common = new Common(webApplicationContext);
    }


    @Test
    void getById() {
    }

    @Test
    void getTopByAuthor() {
    }

    @Test
    void delete() {
    }
}
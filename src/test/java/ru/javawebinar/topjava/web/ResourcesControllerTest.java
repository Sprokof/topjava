package ru.javawebinar.topjava.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.awt.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;


class ResourcesControllerTest extends AbstractControllerTest {

    @Test
    void getUsers() throws Exception {
        perform(MockMvcRequestBuilders.get("/users"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void getMeals() throws Exception {
        perform(MockMvcRequestBuilders.get("/meals"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}

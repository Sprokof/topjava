package ru.javawebinar.topjava.web;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.awt.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ResourcesControllerTest extends AbstractControllerTest {

    static final String CSS_PATH = ResourcesController.CSS_URL;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(CSS_PATH))
                .andExpect(status().isFound());
    }

}

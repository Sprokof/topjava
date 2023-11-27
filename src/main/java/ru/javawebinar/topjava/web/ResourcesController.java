package ru.javawebinar.topjava.web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = ResourcesController.CSS_URL)
public class ResourcesController {

    static final String CSS_URL = "/styles";

    @GetMapping()
    public String get(){
        return "redirect:/topjava/resources/css/style.css";
    }


}

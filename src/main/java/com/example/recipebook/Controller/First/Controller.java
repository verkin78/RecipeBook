package com.example.recipebook.Controller.First;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/")
    public String start() {
        return "Приложение запущено";
    }
}

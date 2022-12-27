package com.example.recipebook.Controller.First;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/")
    public String start() {
        return "Приложение запущено";
    }
    @GetMapping("/info")
    public String start1() {
        return "имя ученика: Вероника Якименко,\n" +
                "название вашего проекта : Книга рецептов,\n" +
                "дату создания проекта : 27.12.2022 г.,\n" +
                "описание проекта в свободной форме: Здесь представлены несколько рецептов, которые будут обновляться" +
                " и дополняться.";
    }

}

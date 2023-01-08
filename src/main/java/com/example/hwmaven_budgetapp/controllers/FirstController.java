package com.example.hwmaven_budgetapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class FirstController {
    String nameOfProject = "First Maven project";

@GetMapping
    public String helloWeb(){
        return "Приложение запущено";
    }
@GetMapping("/info")
    public String info(@RequestParam String name){
        return "Имя ученика - " + name +
                " Название проекта - " + nameOfProject +
                " Дата создания проекта - " + LocalDateTime.now() +
                " Описание проекта - Проект \"hwMaven\".\n" +
                "\t\tФункционал проекта пока ограничен выводом в брауезере инфромации о самом проекте, в будущем, возможно, функционал будет расширяться.\n" +
                "\t\tПроект написан на языке Java и реализован с помощью фреймворка Spring и соответствующих ему библиотек." ;
    }
}

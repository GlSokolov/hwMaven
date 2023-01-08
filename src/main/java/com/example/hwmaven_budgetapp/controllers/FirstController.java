package com.example.hwmaven_budgetapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
@GetMapping
    public String helloWeb(){
        return "Hello web";
    }
@GetMapping("/page")
    public String test(@RequestParam String page){
        return "Hello page " + page;
    }
}

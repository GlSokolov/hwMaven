package com.example.hwmaven_budgetapp.controllers;

import com.example.hwmaven_budgetapp.model.Ingredient;
import com.example.hwmaven_budgetapp.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @PutMapping
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable Integer id) {
        return ingredientService.getIngredient(id);
    }
}

package com.example.hwmaven_budgetapp.controllers;

import com.example.hwmaven_budgetapp.model.Ingredient;
import com.example.hwmaven_budgetapp.model.Recipe;
import com.example.hwmaven_budgetapp.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable Integer id) {
        return ingredientService.getIngredient(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id , @RequestBody Ingredient ingredient) {
        Ingredient newIngredient = ingredientService.editIngredient(id, ingredient);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        } else {return ResponseEntity.ok(newIngredient);}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient (@PathVariable int id) {
        if (ingredientService.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        } else {return ResponseEntity.notFound().build();}
    }

    @GetMapping
    public ResponseEntity<Collection<Ingredient>> getAllIngredients(){
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }
}

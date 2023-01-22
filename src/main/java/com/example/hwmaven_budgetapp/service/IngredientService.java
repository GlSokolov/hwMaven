package com.example.hwmaven_budgetapp.service;

import com.example.hwmaven_budgetapp.model.Ingredient;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);

    Ingredient getIngredient (int number);
}

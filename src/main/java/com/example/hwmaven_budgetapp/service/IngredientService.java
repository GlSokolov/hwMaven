package com.example.hwmaven_budgetapp.service;

import com.example.hwmaven_budgetapp.model.Ingredient;
import com.example.hwmaven_budgetapp.model.Recipe;

import java.util.Collection;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);
    Ingredient getIngredient (int number);
    Ingredient editIngredient (int id, Ingredient ingredient);
    boolean deleteIngredient (int id);
    Collection<Ingredient> getAllIngredients();
}

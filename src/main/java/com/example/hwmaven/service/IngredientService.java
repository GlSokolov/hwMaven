package com.example.hwmaven.service;
import com.example.hwmaven.model.Ingredient;

import java.util.Collection;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);
    Ingredient getIngredient (int number);
    Ingredient editIngredient (int id, Ingredient ingredient);
    boolean deleteIngredient (int id);
    Collection<Ingredient> getAllIngredients();
}

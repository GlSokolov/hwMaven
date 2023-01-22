package com.example.hwmaven_budgetapp.service;

import com.example.hwmaven_budgetapp.model.Recipe;

public interface RecipeService {
    Recipe addRecipe (Recipe recipe);
    Recipe getRecipe (int number);
}

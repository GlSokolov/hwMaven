package com.example.hwmaven.service;

import com.example.hwmaven.model.Recipe;

import java.util.Collection;

public interface RecipeService {
    Recipe addRecipe (Recipe recipe);
    Recipe getRecipe (int number);
    Recipe editRecipe(int id, Recipe recipe);
    boolean deleteRecipe (int id);
    Collection<Recipe> getAllRecipes();

}

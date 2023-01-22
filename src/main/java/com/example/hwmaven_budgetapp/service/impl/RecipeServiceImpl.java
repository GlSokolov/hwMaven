package com.example.hwmaven_budgetapp.service.impl;

import com.example.hwmaven_budgetapp.model.Recipe;
import com.example.hwmaven_budgetapp.service.RecipeService;

import java.util.HashMap;

public class RecipeServiceImpl implements RecipeService {
    private static HashMap<Integer, Recipe> mapOfRecipes = new HashMap<>();
    private static int id = 0;

    @Override
    public Recipe addRecipe (Recipe recipe) {
        mapOfRecipes.put(++id, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipe(int id) {
        return mapOfRecipes.get(id);
    }
}

package com.example.hwmaven.service.impl;

import com.example.hwmaven.model.Recipe;
import com.example.hwmaven.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
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

    @Override
    public Recipe editRecipe(int id, Recipe recipe) {
        if (!mapOfRecipes.containsKey(id)) {
            throw new IllegalArgumentException("<Ингредиент не найден>");
        }
        mapOfRecipes.put(id, recipe);
        return recipe;
    }

    @Override
    public boolean deleteRecipe(int id) {
        if (!mapOfRecipes.containsKey(id)) {
            throw new IllegalArgumentException("<Ингредиент не найден>");
        } else {
            mapOfRecipes.remove(id);
            return true;}
    }

    @Override
    public Collection<Recipe> getAllRecipes() {
        return mapOfRecipes.values();
    }


}

package com.example.hwmaven_budgetapp.service.impl;

import com.example.hwmaven_budgetapp.model.Ingredient;
import com.example.hwmaven_budgetapp.service.IngredientService;

import java.util.Collection;
import java.util.HashMap;

public class IngredientServiceImpl implements IngredientService {
    private static final HashMap<Integer, Ingredient> mapOfIngredient = new HashMap<>();
    private static int id=0;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        mapOfIngredient.put(++id, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(int id) {
        return mapOfIngredient.get(id);
    }

    @Override
    public Ingredient editIngredient(int id, Ingredient ingredient) {
        if (!mapOfIngredient.containsKey(id)) {
            throw new IllegalArgumentException("<Ингредиент не найден>");
        }
        mapOfIngredient.put(id, ingredient);
        return ingredient;
    }

    @Override
    public boolean deleteIngredient(int id) {
        if (!mapOfIngredient.containsKey(id)) {
            throw new IllegalArgumentException("<Ингредиент не найден>");
        } else {
            mapOfIngredient.remove(id);
            return true;}
    }

    @Override
    public Collection<Ingredient> getAllIngredients() {
        return mapOfIngredient.values();
    }
}

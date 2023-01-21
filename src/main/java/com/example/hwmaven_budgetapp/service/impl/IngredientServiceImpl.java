package com.example.hwmaven_budgetapp.service.impl;

import com.example.hwmaven_budgetapp.model.Ingredient;
import com.example.hwmaven_budgetapp.service.IngredientService;

import java.util.HashMap;

public class IngredientServiceImpl implements IngredientService {
    private static HashMap<Integer, Ingredient> mapOfIngredient = new HashMap<>();
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
}

package com.example.hwmaven.service.impl;

import com.example.hwmaven.model.Ingredient;
import com.example.hwmaven.service.FilesService;
import com.example.hwmaven.service.IngredientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
@Service
public class IngredientServiceImpl implements IngredientService {
    private static HashMap<Integer, Ingredient> mapOfIngredient = new HashMap<>();
    private static int id=0;
    @Value("${name.of.ingredients.date.file}")
    private String dataFileName;
    final private FilesService filesService;

    public IngredientServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }


    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        if (ingredient == null) {
            throw new IllegalArgumentException("<Неверно указан ингредиент>");
        }
        mapOfIngredient.put(++id, ingredient);
        saveToFile();
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(int id) {
        if (!mapOfIngredient.containsKey(id)) {
            throw new IllegalArgumentException("<Ингредиент не найден>");
        }
        return mapOfIngredient.get(id);
    }

    @Override
    public Ingredient editIngredient(int id, Ingredient ingredient) {
        if (!mapOfIngredient.containsKey(id)) {
            throw new IllegalArgumentException("<Ингредиент не найден>");
        }
        mapOfIngredient.put(id, ingredient);
        saveToFile();
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

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(mapOfIngredient);
            filesService.saveToFile(json, dataFileName);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile(){
        try {
            String json = filesService.readFromFile(dataFileName);
            mapOfIngredient = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @PostConstruct
    public void init(){
        readFromFile();
    }
}

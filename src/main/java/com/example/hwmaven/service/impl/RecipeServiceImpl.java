package com.example.hwmaven.service.impl;

import com.example.hwmaven.model.Recipe;
import com.example.hwmaven.service.FilesService;
import com.example.hwmaven.service.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static HashMap<Integer, Recipe> mapOfRecipes = new HashMap<>();
    private static int id = 0;

    @Value("${name.of.recipes.date.file}")
    private String dataFileName;

    final private FilesService filesService;

    public RecipeServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @Override
    public Recipe addRecipe (Recipe recipe) {
        if (recipe == null) {
            throw new IllegalArgumentException("<Неверно указан рецепт>");
        }
        mapOfRecipes.put(++id, recipe);
        saveToFile();
        return mapOfRecipes.get(id);

    }

    @Override
    public Recipe getRecipe(int id) {
        if (!mapOfRecipes.containsKey(id)) {
            throw new IllegalArgumentException("<Рецепт не найден>");
        }
        return mapOfRecipes.get(id);
    }

    @Override
    public Recipe editRecipe(int id, Recipe recipe) {
        if (!mapOfRecipes.containsKey(id)) {
            throw new IllegalArgumentException("<Рецепт не найден>");
        }
        mapOfRecipes.put(id, recipe);
        saveToFile();
        return recipe;
    }

    @Override
    public boolean deleteRecipe(int id) {
        if (!mapOfRecipes.containsKey(id)) {
            throw new IllegalArgumentException("<Рецепт не найден>");
        } else {
            mapOfRecipes.remove(id);
            return true;}
    }

    @Override
    public Collection<Recipe> getAllRecipes() {
        return mapOfRecipes.values();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(mapOfRecipes);
            filesService.saveToFile(json, dataFileName);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile(){
        try {
            String json = filesService.readFromFile(dataFileName);
            mapOfRecipes = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Recipe>>() {
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

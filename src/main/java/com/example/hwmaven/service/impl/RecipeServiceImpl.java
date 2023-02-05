package com.example.hwmaven.service.impl;

import com.example.hwmaven.model.Recipe;
import com.example.hwmaven.service.FilesService;
import com.example.hwmaven.service.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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
            throw new NotFoundException("<Рецепт не найден>");
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
//            DataFile dataFile = new DataFile(id + 1, mapOfRecipes);
//            String json = new ObjectMapper().writeValueAsString(dataFile);
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
//    private void readFromFile(){
//        try {
//            String json = filesService.readFromFile(dataFileName);
//            DataFile dataFile = new ObjectMapper().readValue(json, new TypeReference<DataFile>() {
//            });
//            id = dataFile.getId();
//            mapOfRecipes = dataFile.getRecipes();
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    private static class DataFile {
//        private int id;
//        private HashMap<Integer, Recipe> recipes;
//
//    }
    @PostConstruct
    public void init(){
        try {
            readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void addRecipeInputStream(InputStream inputStream) throws IOException {
//
//        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
//            String line;
//            while ((line = reader.readLine()) != null){
//                String[] array = StringUtils.split(line,'|');
//                new Ingredient()
//            }
//        }
//    }
    @Override
    public Path createAllRecipesReport () throws IOException {
        mapOfRecipes.getOrDefault(id, null);
        Path recipes = filesService.createTempFile("Recipes");
        try (Writer writer = Files.newBufferedWriter(recipes, StandardCharsets.UTF_8)) {
            for (Recipe recipe : mapOfRecipes.values()) {
                StringBuilder ingredients = new StringBuilder();
                StringBuilder steps = new StringBuilder();
                for (String ingredient : recipe.getProducts()){
                    ingredients.append(ingredient).append(", \n");
                }
                for (String instructions : recipe.getInstruction()) {
                    steps.append("\r\n").append(instructions);
                }
                writer.append(recipe.getName()).append("\r\n")
                        .append("Время готовки: ").append(String.valueOf(recipe.getCookingTime())).append(" минут ").append("\r\n")
                        .append("Ингредиенты: ").append(ingredients.toString()).append("\r\n")
                        .append("Инструкция: ").append(steps.toString());
                writer.append("\r\n");
            }
        }
        return recipes;
    }

}

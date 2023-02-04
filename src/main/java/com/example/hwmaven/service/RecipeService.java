package com.example.hwmaven.service;

import com.example.hwmaven.model.Recipe;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

public interface RecipeService {
    Recipe addRecipe (Recipe recipe);
    Recipe getRecipe (int number);
    Recipe editRecipe(int id, Recipe recipe);
    boolean deleteRecipe (int id);
    Collection<Recipe> getAllRecipes();

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
    Path createAllRecipesReport() throws IOException;
}

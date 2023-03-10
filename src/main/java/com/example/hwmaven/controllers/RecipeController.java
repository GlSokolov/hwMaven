package com.example.hwmaven.controllers;

import com.example.hwmaven.model.Recipe;
import com.example.hwmaven.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@Tag(name = "Рецепты", description = "Операции с рецептами:")
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping
    @Operation(summary = "Добавление рецепта",description = "Тут типа описание")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт добавлен", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Recipe.class)))})
    })
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение рецепта",description = "Тут типа описание")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепты получены", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Recipe.class)))})
    })
    public Recipe getRecipe(@PathVariable Integer id) {
        return recipeService.getRecipe(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование рецепта",description = "Тут типа описание")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт отредактирован", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Recipe.class)))})
    })
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id , @RequestBody Recipe recipe) {
        Recipe newRecipe = recipeService.editRecipe(id, recipe);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        } else {return ResponseEntity.ok(newRecipe);}
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление рецепта",description = "Тут типа описание")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт удален", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Recipe.class)))})
    })
    public ResponseEntity<Void> deleteRecipe (@PathVariable int id) {
        if (recipeService.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        } else {return ResponseEntity.notFound().build();}
    }

    @GetMapping
    @Operation(summary = "Получение всех рецептов",description = "Тут типа описание")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепты найдены", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Recipe.class)))})
    })
    public ResponseEntity<Collection<Recipe>> getAllRecipes(){
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @GetMapping(value = "/recipes.txt", produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(summary = "Получение всех рецептов в формате txt",description = "Тут типа описание")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Рецепты"),
            @ApiResponse(responseCode = "400",description = "Ошибка запроса"),
            @ApiResponse(responseCode = "400",description = "Неверный URL"),
            @ApiResponse(responseCode = "500",description = "Ошибка сервера")
    })
    public ResponseEntity<InputStreamResource> getAllRecipesTxt() {
        try {
            Path path = recipeService.createAllRecipesReport();
            if (Files.size(path) == 0) {
                return ResponseEntity.noContent().build();
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN)
                    .contentLength(Files.size(path))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "txt\"")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }
}
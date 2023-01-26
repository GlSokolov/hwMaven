package com.example.hwmaven.controllers;

import com.example.hwmaven.model.Ingredient;
import com.example.hwmaven.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
@Tag(name = "Ингредиенты", description = "Операции с ингредиентами:")
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    @Operation(summary = "Добавление ингредиента",description = "Тут типа описание")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент добавлен", content = {
                    @Content(mediaType = "Тут пока чета не пон", array = @ArraySchema(schema = @Schema(implementation = Ingredient.class)))})
    })
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение ингредиента",description = "Тут типа описание")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиенты получены", content = {
                    @Content(mediaType = "Вроде пон но не совсем", array = @ArraySchema(schema = @Schema(implementation = Ingredient.class)))})
    })
    public Ingredient getIngredient(@PathVariable Integer id) {
        return ingredientService.getIngredient(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование ингредиента",description = "Тут типа описание")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент отредактирован", content = {
                    @Content(mediaType = "Вроде пон", array = @ArraySchema(schema = @Schema(implementation = Ingredient.class)))})
    })
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id , @RequestBody Ingredient ingredient) {
        Ingredient newIngredient = ingredientService.editIngredient(id, ingredient);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        } else {return ResponseEntity.ok(newIngredient);}
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление ингредиента",description = "Тут типа описание")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиенты удаленеы", content = {
                    @Content(mediaType = "Вроде пон", array = @ArraySchema(schema = @Schema(implementation = Ingredient.class)))})
    })
    public ResponseEntity<Void> deleteIngredient (@PathVariable int id) {
        if (ingredientService.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        } else {return ResponseEntity.notFound().build();}
    }

    @GetMapping
    @Operation(summary = "Получение всех ингредиентов",description = "Тут типа описание")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиенты найдены", content = {
                    @Content(mediaType = "Вроде пон", array = @ArraySchema(schema = @Schema(implementation = Ingredient.class)))})
    })
    public ResponseEntity<Collection<Ingredient>> getAllIngredients(){
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }
}

package com.example.recipebook.Controllers;

import com.example.recipebook.Models.Recipe;
import com.example.recipebook.Services.RecipeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Рецепты", description = "действия, относящиеся к работе с рецептами")
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity getRecipeId(@PathVariable Long recipeId) {
        Recipe recipe = recipeService.getRecipeId(recipeId);
        if(recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @PostMapping("/create")
    public ResponseEntity createRecipe (@RequestBody Recipe recipe) {
        Recipe createdRecipe = recipeService.createRecipe(recipe);
        return ResponseEntity.ok(createdRecipe);
    }

    @GetMapping("/allrecipe")
    @Operation(description = "Показать список всех рецептов")
    @ApiResponse(responseCode = "200",
            description = "Найдены следующие рецепты")
    public ResponseEntity getAllIngredients() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @DeleteMapping("{recipeId}")
    public ResponseEntity deleteIngredient(@PathVariable Long recipeId) {
        return ResponseEntity.ok(recipeService.deleteRecipe(recipeId));
    }

    @PutMapping("{recipeId}")
    @Operation(description = "Показать список всех рецептов")
    @ApiResponse(responseCode = "200",
            description = "Найдены следующие рецепты")
    public ResponseEntity changeIngredient(@PathVariable Long recipeId, @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.changeRecipeById(recipeId, recipe));
    }
}
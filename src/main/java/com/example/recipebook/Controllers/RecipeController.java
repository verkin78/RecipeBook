package com.example.recipebook.Controllers;

import com.example.recipebook.Models.Recipe;
import com.example.recipebook.Services.RecipeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

@RestController
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
    public ResponseEntity getAllIngredients() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @DeleteMapping("/delete/{recipeId}")
    public ResponseEntity deleteIngredient(@PathVariable Long recipeId) {
        return ResponseEntity.ok(recipeService.deleteRecipe(recipeId));
    }

    @PutMapping("/change/{recipeId}")

    public ResponseEntity changeIngredient(@PathVariable Long recipeId, @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.changeRecipeById(recipeId, recipe));
    }
}
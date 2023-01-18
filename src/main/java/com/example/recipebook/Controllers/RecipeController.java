package com.example.recipebook.Controllers;

import com.example.recipebook.Models.Recipe;
import com.example.recipebook.Services.RecipeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@Tag(name = "Рецепты", description = "действия, относящиеся к работе с рецептами")
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{recipeId}")
    @Operation(description = "Найти рецепт по ид")
    @ApiResponse(responseCode = "200",
            description = "Найден рецепт")
    public ResponseEntity getRecipeId(@PathVariable Long recipeId) {
        Recipe recipe = recipeService.getRecipeId(recipeId);
        if(recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @PostMapping("/create")
    @Operation(description = "Создать рецепт")
    @ApiResponse(responseCode = "200",
            description = "Рецепт создан")
    public ResponseEntity createRecipe (@RequestBody Recipe recipe) {
        Recipe createdRecipe = recipeService.createRecipe(recipe);
        return ResponseEntity.ok(createdRecipe);
    }

    @GetMapping("/allrecipe")
    @Operation(description = "Показать список всех рецептов")
    @ApiResponse(responseCode = "200",
            description = "Найдены следующие рецепты")
    public ResponseEntity getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @DeleteMapping("{recipeId}")
    @Operation(description = "Удалить рецепт")
    @ApiResponse(responseCode = "200",
            description = "Рецепт удалён")
    public ResponseEntity deleteRecipe(@PathVariable Long recipeId) {
        return ResponseEntity.ok(recipeService.deleteRecipe(recipeId));
    }

    @PutMapping("{recipeId}")
    @Operation(description = "Найти рецепт по ид")
    @ApiResponse(responseCode = "200",
            description = "Найдены следующие рецепты")
    public ResponseEntity changeRecipe(@PathVariable Long recipeId, @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.changeRecipeById(recipeId, recipe));
    }

    @GetMapping("/download/{id}")
    @Operation(description = "Загрузка рецепта по id")
    @ApiResponse(responseCode = "200",
            description = "Successfully")
    public  ResponseEntity downloadRecipeById(@PathVariable Long id) {
        try {
            Path path = recipeService.CreateRecipeTextFile(id);
            InputStreamResource inputStream = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipe.doc\"")
                    .contentLength(Files.size(path))
                    .body(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @GetMapping("/download/all")
    @Operation(description = "Загрузка всех рецептов")
    @ApiResponse(responseCode = "200",
            description = "Successfully")
    public  ResponseEntity downloadAllRecipes() {
        try {
            Path path = recipeService.CreateRecipeTextFileAll();
            InputStreamResource inputStream = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"AllRecipes.doc\"")
                    .contentLength(Files.size(path))
                    .body(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
package com.example.recipebook.Services;

import com.example.recipebook.Models.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static Long recipeId = 1L;
    private static Map<Long, Recipe> recipes = new HashMap<>();
    private final FileRecipeService fileRecipeService;

    public RecipeServiceImpl(FileRecipeService fileRecipeService) {
        this.fileRecipeService = fileRecipeService;
    }

    @PostConstruct
    private void init() {
        readToFile();
    }

    @Override
    @Nullable
    public Recipe createRecipe(Recipe recipe) {
        if (!recipes.containsKey(recipeId)) {
            recipes.put(recipeId++, recipe);
            saveFile();
            return recipe;
        }
        recipeId++;
        createRecipe(recipe);
        return recipe;
    }

    @Override
    @Nullable
    public Recipe getRecipeId(Long recipeId) {
        return recipes.get(recipeId);
    }

    @Override
    @Nullable
    public boolean changeRecipeById(Long recipeId, Recipe recipe) {
        if (recipes.containsKey(recipeId)) {
            recipes.put(recipeId, recipe);
            saveFile();
            return true;
        }
        return false;
    }

    @Override
    @Nullable
    public boolean deleteRecipe(Long recipeId) {
        if (recipes.containsKey(recipeId)) {
            recipes.remove(recipeId);
            saveFile();
            return true;
        }
        return false;
    }

    @Override
    @Nullable
    public Map<Long, Recipe> getAllRecipes() {
        return new HashMap<>(recipes);
    }

    public void saveFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            fileRecipeService.saveFileRecipe(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readToFile() {
        try {
            String json = fileRecipeService.readFileRecipe();
            recipes = new ObjectMapper().readValue(json, new TypeReference<Map<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Path CreateRecipeTextFile(Long recipeId) throws IOException {
        Path path = fileRecipeService.CreateTempFile("RecipeFile");
        try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)){
            writer.append(recipes.get(recipeId).getRecipeName() + "\n" +
                    recipes.get(recipeId).getKookTime().toString() + "\n" +
                    "Ингредиенты:" + "\n" +
                    recipes.get(recipeId).getIngredients() + "\n" +
                    "Инструкция приготовления: " + "\n" +
                    recipes.get(recipeId).getIngredients() + "\n");
        }
        return path;
    }

    @Override
    public Path CreateRecipeTextFileAll() throws IOException {
        Path path = fileRecipeService.CreateTempFile("RecipeFiles");
        try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)){
            writer.append(recipes.toString());
        }
        return path;
    }
}

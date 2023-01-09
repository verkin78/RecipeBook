package com.example.recipebook.Services;

import com.example.recipebook.Models.Recipe;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static Long recipeId =0L;
    Map<Long, Recipe> recipes = new HashMap<>();

    @Override
    public Recipe createRecipe(Recipe recipe) {
        recipes.put(recipeId, recipe);
        recipeId++;
        return recipe;
    }

    @Override
    public Recipe getRecipeId(Long recipeId) {
        return recipes.get(recipeId);
    }

    @Override
    public boolean changeRecipeById(Long recipeId) {
        if (recipes.containsKey(recipeId)) {
            recipes.remove(recipeId);
            return true;
        }
        return false;
    }
}

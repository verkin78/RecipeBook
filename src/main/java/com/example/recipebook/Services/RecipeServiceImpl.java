package com.example.recipebook.Services;

import com.example.recipebook.Models.Recipe;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static Long recipeId = 1L;
    private final Map<Long, Recipe> recipes = new HashMap<>();

    @Override
    @Nullable
    public Recipe createRecipe(Recipe recipe) {
        recipes.put(recipeId++, recipe);
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
            return true;
        }
        return false;
    }

    @Override
    @Nullable
    public boolean deleteRecipe(Long recipeId) {
        if (recipes.containsKey(recipeId)) {
            recipes.remove(recipeId);
            return true;
        }
        return false;
    }

    @Override
    @Nullable
    public Map<Long, Recipe> getAllRecipes() {
        return new HashMap<>(recipes);
    }
}

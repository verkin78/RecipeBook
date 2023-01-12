package com.example.recipebook.Services;

import com.example.recipebook.Models.Recipe;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public interface RecipeService {
    Recipe createRecipe(Recipe recipe);

    Recipe getRecipeId(Long recipeId);


    boolean changeRecipeById(Long recipeId, Recipe recipe);

    boolean deleteRecipe(Long recipeId);

    Map<Long, Recipe> getAllRecipes();
}

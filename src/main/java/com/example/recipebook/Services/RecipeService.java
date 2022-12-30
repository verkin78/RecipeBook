package com.example.recipebook.Services;

import com.example.recipebook.Models.Recipe;
;
import org.springframework.stereotype.Service;

@Service
public interface RecipeService {
    Recipe createRecipe(Recipe recipe);
    Recipe getRecipeId(Long recipeId);
}

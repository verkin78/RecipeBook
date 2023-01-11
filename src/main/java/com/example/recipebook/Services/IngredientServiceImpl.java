package com.example.recipebook.Services;

import com.example.recipebook.Models.Ingredient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static Long ingredientId = 0L;
    private Map<Long, Ingredient> ingredients = new HashMap<>();

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredients.put(ingredientId, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredientId(Long ingredientId) {
        return ingredients.get(ingredientId);
    }

    @Override
    public boolean setIngredientById(Long ingredientId, Ingredient ingredient) {
        if (ingredients.containsKey(ingredientId)) {
            ingredients.put(ingredientId, ingredient);
            return true;
        }
        return false;
    }

    @Override
    public Ingredient deleteIngredient(Long ingredientId) {
        return ingredients.remove(ingredientId);
    }

    @Override
    public Map<Long, Ingredient> getAllIngredients() {
        return ingredients;
    }
}

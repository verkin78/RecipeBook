package com.example.recipebook.Services;

import com.example.recipebook.Models.Ingredient;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static Long ingredientId = 1L;
    private final Map<Long, Ingredient> ingredients = new HashMap<>();

    @Override
    @Nullable
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredients.put(ingredientId++, ingredient);
        return ingredient;
    }

    @Override
    @Nullable
    public Ingredient getIngredientId(Long ingredientId) {
        return ingredients.get(ingredientId);
    }

    @Override
    @Nullable
    public boolean setIngredientById(Long ingredientId, Ingredient ingredient) {
        if (ingredients.containsKey(ingredientId)) {
            ingredients.put(ingredientId, ingredient);
            return true;
        }
        return false;
    }

    @Override
    @Nullable
    public Ingredient deleteIngredient(Long ingredientId) {
        return ingredients.remove(ingredientId);
    }

    @Override
    @Nullable
    public Map<Long, Ingredient> getAllIngredients() {
        return new HashMap<>(ingredients);
    }
}

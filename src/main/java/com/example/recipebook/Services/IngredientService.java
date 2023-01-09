package com.example.recipebook.Services;

import com.example.recipebook.Models.Ingredient;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface IngredientService {

    Ingredient addIngredient(Ingredient ingredient);
    Ingredient getIngredientId(Long ingredientId);

    boolean setIngredientById(Long ingredientId, Ingredient ingredient);

    Ingredient deleteIngredient(Long ingredientId);

    Map<Long, Ingredient> getAllIngredients();
}

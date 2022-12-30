package com.example.recipebook.Services;

import com.example.recipebook.Models.Ingredient;

import org.springframework.stereotype.Service;

@Service
public interface IngredientService {

    Ingredient addIngredient(Ingredient ingredient);
    Ingredient getIngredientId(Long ingredientId);
}

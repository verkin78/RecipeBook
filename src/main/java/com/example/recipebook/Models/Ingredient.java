package com.example.recipebook.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
public class Ingredient {
    private final String ingredientName;
    private final Integer numOfIngredients;
    private final String unit;
}

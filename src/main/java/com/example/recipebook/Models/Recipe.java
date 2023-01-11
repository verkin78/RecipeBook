package com.example.recipebook.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Recipe {
    private final String recipeName;
    private final Integer kookTime;
    private final Ingredient[] ingredients;
    private final String[] kookSteps;
}

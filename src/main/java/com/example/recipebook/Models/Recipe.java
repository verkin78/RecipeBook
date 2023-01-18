package com.example.recipebook.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NonNull
@NoArgsConstructor
public class Recipe {
    private  String recipeName;
    private  Integer kookTime;
    private  Ingredient[] ingredients;
    private  String[] kookSteps;
}

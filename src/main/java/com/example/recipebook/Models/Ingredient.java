package com.example.recipebook.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NonNull
public class Ingredient {
    private  String ingredientName;
    private  Integer numOfIngredients;
    private  String unit;
}

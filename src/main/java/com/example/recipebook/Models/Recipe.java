package com.example.recipebook.Models;

import java.util.ArrayList;
import java.util.LinkedList;
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

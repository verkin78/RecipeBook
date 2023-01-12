package com.example.recipebook.Services;

public interface FileRecipeService {
    boolean saveFileRecipe(String json);

    String readFileRecipe();

    boolean cleanDataFileRecipe();
}

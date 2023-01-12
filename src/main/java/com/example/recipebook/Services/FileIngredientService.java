package com.example.recipebook.Services;

public interface FileIngredientService {
    boolean saveFileIngredient(String json);

    String readFileIngredient();

    boolean cleanDataFileIngredient();
}

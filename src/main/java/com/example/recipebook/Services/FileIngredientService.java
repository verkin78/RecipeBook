package com.example.recipebook.Services;

import java.io.File;

public interface FileIngredientService {
    boolean saveFileIngredient(String json);

    String readFileIngredient();

    boolean cleanDataFileIngredient();

    File getDataFile();
}

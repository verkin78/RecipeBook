package com.example.recipebook.Services;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface FileRecipeService {

    boolean saveFileRecipe(String json);

    String readFileRecipe();

    boolean cleanDataFileRecipe();

    File getDataFile();
}

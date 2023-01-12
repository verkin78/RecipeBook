package com.example.recipebook.Services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public interface FileRecipeService {

    boolean saveFileRecipe(String json);

    String readFileRecipe();

    boolean cleanDataFileRecipe();
}

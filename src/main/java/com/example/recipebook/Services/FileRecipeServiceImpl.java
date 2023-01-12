package com.example.recipebook.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileRecipeServiceImpl implements FileRecipeService{

    @Value("${name.of.data.file}")
    private String fileName;

    @Value("${path.to.data.file}")
    private String fileDataPath;

    @Override
    public boolean saveFileRecipe(String json) {
        try {
            cleanDataFileRecipe();
            Files.writeString(Path.of(fileDataPath), json);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readFileRecipe() {
        try {
            return Files.readString(Path.of(fileDataPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cleanDataFileRecipe() {
        try {
            Files.deleteIfExists(Path.of(fileDataPath));
            Files.createFile(Path.of(fileDataPath));
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

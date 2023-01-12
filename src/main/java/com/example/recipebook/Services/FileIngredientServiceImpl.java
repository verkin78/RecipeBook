package com.example.recipebook.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
@Service
public class FileIngredientServiceImpl implements FileIngredientService{

    @Value("${name.of.data.file1}")
    private String fileName;

    @Value("${path.to.data.file1}")
    private String fileDataPath;

    @Override
    public boolean saveFileIngredient(String json) {
        try {
            cleanDataFileIngredient();
            Files.writeString(Path.of(fileDataPath,fileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readFileIngredient() {
        try {
            return Files.readString(Path.of(fileDataPath,fileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cleanDataFileIngredient() {
        try {
            Files.deleteIfExists(Path.of(fileDataPath,fileName));
            Files.createFile(Path.of(fileDataPath,fileName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

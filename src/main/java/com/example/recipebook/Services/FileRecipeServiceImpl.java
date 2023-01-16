package com.example.recipebook.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileRecipeServiceImpl implements FileRecipeService {

    @Value("${name.of.data.file}")
    private String fileName;

    @Value("${path.to.data.file}")
    private String fileDataPath;

    @Override
    public boolean saveFileRecipe(String json) {
        try {
            cleanDataFileRecipe();
            Files.writeString(Path.of(fileDataPath, fileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readFileRecipe() {
        try {
            return Files.readString(Path.of(fileDataPath, fileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cleanDataFileRecipe() {
        try {
            Files.deleteIfExists(Path.of(fileDataPath, fileName));
            Files.createFile(Path.of(fileDataPath, fileName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public File getDataFile() {
        return new File(fileDataPath + "/" + fileName);
    }

    @Override
    public Path CreateTempFile(String suffix) {
        try {
            return Files.createTempFile(Path.of(fileDataPath), "tenpfile", suffix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

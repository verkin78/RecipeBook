package com.example.recipebook.Controllers;

import com.example.recipebook.Services.FileRecipeService;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files/recipe")
public class FilesRecipeController {

    private final FileRecipeService fileRecipeService;

    public FilesRecipeController(FileRecipeService fileRecipeService) {
        this.fileRecipeService = fileRecipeService;
    }

    @GetMapping(value = "/export", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> downloadFile() throws FileNotFoundException {
        File file = fileRecipeService.getDataFile();
        if (file.exists()) {
            InputStreamResource inputStream = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipe.json\"")
                    .contentLength(file.length())
                    .body(inputStream);

        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile file) {
        fileRecipeService.cleanDataFileRecipe();
        File file1 = fileRecipeService.getDataFile();
        try (FileOutputStream fileOutputStream = new FileOutputStream(file1)){
            IOUtils.copy(file.getInputStream(), fileOutputStream);
            ResponseEntity.ok().build();
        }    catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
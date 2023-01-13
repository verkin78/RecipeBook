package com.example.recipebook.Controllers;

import com.example.recipebook.Services.FileIngredientService;
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
@RequestMapping("/files/Ingredient")
public class FilesIngredientController {

    private final FileIngredientService fileIngredientService;

    public FilesIngredientController(FileIngredientService fileIngredientService) {
        this.fileIngredientService = fileIngredientService;
    }


    @GetMapping(value = "/export", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> downloadFileIng() throws FileNotFoundException {
        File fileIng = fileIngredientService.getDataFile();
        if (fileIng.exists()) {
            InputStreamResource inputStream = new InputStreamResource(new FileInputStream(fileIng));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Ingredient.json\"")
                    .contentLength(fileIng.length())
                    .body(inputStream);

        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFileIng(@RequestParam MultipartFile file) {
        fileIngredientService.cleanDataFileIngredient();
        File file2 = fileIngredientService.getDataFile();
        try (FileOutputStream fileOutputStream = new FileOutputStream(file2)) {
            IOUtils.copy(file.getInputStream(), fileOutputStream);
            ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

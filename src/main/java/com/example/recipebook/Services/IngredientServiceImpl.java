package com.example.recipebook.Services;

import com.example.recipebook.Models.Ingredient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static Long ingredientId = 1L;
    private static Map<Long, Ingredient> ingredients = new HashMap<>();
    private final FileIngredientService fileIngredientService;

    public IngredientServiceImpl(FileIngredientService fileIngredientService) {
        this.fileIngredientService = fileIngredientService;
    }

    @PostConstruct
    private void init() {
        readToFile();
    }


    @Override
    @Nullable
    public Ingredient addIngredient(Ingredient ingredient) {
        if (!ingredients.containsKey(ingredientId)) {
            ingredients.put(ingredientId++, ingredient);
            saveFile();
            return ingredient;
        }
        ingredientId++;
        addIngredient(ingredient);
        return ingredient;
    }

    @Override
    @Nullable
    public Ingredient getIngredientId(Long ingredientId) {
        return ingredients.get(ingredientId);
    }

    @Override
    @Nullable
    public boolean setIngredientById(Long ingredientId, Ingredient ingredient) {
        if (ingredients.containsKey(ingredientId)) {
            ingredients.put(ingredientId, ingredient);
            saveFile();
            return true;
        }
        return false;
    }

    @Override
    @Nullable
    public boolean deleteIngredient(Long ingredientId) {
        if (ingredients.containsKey(ingredientId)) {
            ingredients.remove(ingredientId);
            saveFile();
            return true;
        }
        return false;
    }

    @Override
    @Nullable
    public Map<Long, Ingredient> getAllIngredients() {
        return new HashMap<>(ingredients);
    }
    public void saveFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            fileIngredientService.saveFileIngredient(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readToFile() {

        try {
            String json = fileIngredientService.readFileIngredient();
            ingredients = new ObjectMapper().readValue(json, new TypeReference<Map<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}

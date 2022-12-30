package com.example.recipebook.Controller.First;

import com.example.recipebook.models.Ingredient;
import com.example.recipebook.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;


    }

    @GetMapping("get/{ingredientId}")
    @ResponseBody
    public ResponseEntity getUser(@PathVariable Long ingredientId) {
        Ingredient ingredient = ingredientService.getIngredientId(ingredientId);
        if(ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity createUser(@RequestBody Ingredient ingredient) {
        Ingredient createdIngredient = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(createdIngredient);
    }
}
package com.example.recipebook.Controllers;


import com.example.recipebook.Models.Ingredient;
import com.example.recipebook.Services.IngredientService;
;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;

    }

    @GetMapping("/{ingredientId}")
    public ResponseEntity getUser(@PathVariable Long ingredientId) {
        Ingredient ingredient = ingredientService.getIngredientId(ingredientId);
        if(ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @GetMapping("/allingredients")
    public ResponseEntity getAllIngredients() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody Ingredient ingredient) {
        Ingredient createdIngredient = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(createdIngredient);
    }

    @DeleteMapping("/delete/{ingredientId}")
    public ResponseEntity deleteIngredient(@PathVariable Long ingredientId) {
        return ResponseEntity.ok(ingredientService.deleteIngredient(ingredientId));
    }

    @PutMapping("/change/{ingredientId}")

    public ResponseEntity changeIngredient(@PathVariable Long ingredientId, @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.setIngredientById(ingredientId, ingredient));
    }


}
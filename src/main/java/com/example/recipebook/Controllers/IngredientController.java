package com.example.recipebook.Controllers;


import com.example.recipebook.Models.Ingredient;
import com.example.recipebook.Services.IngredientService;
;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Ингредиенты", description = "всё, что относится к ингредиентам")
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{ingredientId}")
    @Operation(description = "Найти ингредиент по номеру")
    @ApiResponse(responseCode = "200",
            description = "Найден ингредиент")
    public ResponseEntity getIngredient(@PathVariable Long ingredientId) {
        Ingredient ingredient = ingredientService.getIngredientId(ingredientId);
        if(ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @GetMapping("/allingredients")
    @Operation(description = "Показать ингредиенты")
    @ApiResponse(responseCode = "200",
            description = "Ингредиенты: ")
    public ResponseEntity getAllIngredients() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @PostMapping
    @Operation(description = "Добавить ингредиент")
    @ApiResponse(responseCode = "200",
            description = "Успешно!")
    public ResponseEntity createШтпкувшуте(@RequestBody Ingredient ingredient) {
        Ingredient createdIngredient = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(createdIngredient);
    }

    @DeleteMapping("{ingredientId}")
    @Operation(description = "Удалить ингредиент")
    @ApiResponse(responseCode = "200",
            description = "Успешно")
    public ResponseEntity deleteIngredient(@PathVariable Long ingredientId) {
        return ResponseEntity.ok(ingredientService.deleteIngredient(ingredientId));
    }

    @PutMapping("{ingredientId}")
    @Operation(description = "Изменить ингредиент")
    @ApiResponse(responseCode = "200",
            description = "Ингредиент изменён")
    public ResponseEntity changeIngredient(@PathVariable Long ingredientId, @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.setIngredientById(ingredientId, ingredient));
    }


}
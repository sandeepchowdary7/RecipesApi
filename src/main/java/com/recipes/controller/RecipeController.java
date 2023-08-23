package com.recipes.controller;

import com.recipes.StatusEnum;
import com.recipes.dto.RecipeDto;
import com.recipes.dto.ResponseDto;
import com.recipes.model.Recipe;
import com.recipes.repository.RecipeRepository;
import com.recipes.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/recipes")
@Slf4j
public class RecipeController {

    private final RecipeRepository recipeRepository;
    private final RecipeService recipeService;

    public RecipeController(RecipeRepository recipeRepository, RecipeService recipeService) {
        this.recipeRepository = recipeRepository;
        this.recipeService = recipeService;
    }

    @GetMapping("/getAllRecipes")
    public List<Recipe> getAllRecipes() {
        Iterable<Recipe> recipes = recipeRepository.findAll();
        return StreamSupport.stream(recipes.spliterator(), false)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public ResponseDto addRecipes(@RequestBody RecipeDto recipe) {
        log.info("recipe" +recipe.toString());
        String response = recipeService.addRecipe(recipe);
        return getResponse(response);
    }
    private ResponseDto getResponse(String response) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(StatusEnum.SUCCESSFUL);
        responseDto.setContent(response);
        return responseDto;
    }
    @GetMapping("/{name}")
    public ResponseEntity<Recipe> getRecipeByName(@PathVariable("name") String name) {
    Optional<Recipe> recipe = recipeRepository.findByName(name);
        if (recipe.isPresent()) {
            return ResponseEntity.ok(recipe.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
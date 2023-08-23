package com.recipes.controller;

import com.recipes.model.Category;
import com.recipes.model.Recipe;
import com.recipes.repository.CategoryRepository;
import com.recipes.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/categories")
@Slf4j
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final RecipeService recipeService;

    public CategoryController(CategoryRepository categoryRepository, RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.recipeService = recipeService;
    }
    @GetMapping("/getCategories")
    public List<Category> getAllCategories() {
        Iterable<Category> categories = categoryRepository.findAll();
        return StreamSupport.stream(categories.spliterator(), false)
                .collect(Collectors.toList());
    }
    @GetMapping(value = "{category}")
    public Set<Recipe> findRecipeByCategory(@PathVariable String category){
        return recipeService.getAllRecipeByCategory(category);
    }
}
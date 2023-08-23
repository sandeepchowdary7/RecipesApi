package com.recipes.service;

import com.recipes.dto.RecipeDto;
import com.recipes.model.Recipe;

import java.util.Set;

public interface RecipeService {
    String addRecipe(RecipeDto recipe);
    Set<Recipe> getAllRecipeByCategory(String category);
}

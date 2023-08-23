package com.recipes.controller;

import com.recipes.model.Category;
import com.recipes.model.Recipe;
import com.recipes.repository.CategoryRepository;
import com.recipes.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategories_ReturnsListOfCategories() {
        // Arrange
        Category category = new Category();
        category.setName("Cookie");
        List<Category> expectedCategories = new ArrayList<>();
        expectedCategories.add(category);

        when(categoryRepository.findAll()).thenReturn(expectedCategories);

        // Act
        List<Category> actualCategories = categoryController.getAllCategories();

        // Assert
        assertEquals(expectedCategories, actualCategories);
        verify(categoryRepository).findAll();
    }

    @Test
    void findRecipeByCategory_ReturnsRecipeSet() {
        // Arrange
        String category = "Category";
        Set<Recipe> expectedRecipes = new HashSet<>(Arrays.asList(
                new Recipe("Recipe 1"),
                new Recipe("Recipe 2")
        ));

        when(recipeService.getAllRecipeByCategory(category)).thenReturn(expectedRecipes);

        // Act
        Set<Recipe> actualRecipes = categoryController.findRecipeByCategory(category);

        // Assert
        assertEquals(expectedRecipes, actualRecipes);
        verify(recipeService).getAllRecipeByCategory(category);
    }
}
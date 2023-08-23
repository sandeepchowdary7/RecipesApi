package com.recipes.controller;

import com.recipes.StatusEnum;
import com.recipes.dto.RecipeDto;
import com.recipes.dto.ResponseDto;
import com.recipes.model.Recipe;
import com.recipes.repository.RecipeRepository;
import com.recipes.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RecipeControllerTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllRecipes_ReturnsListOfRecipes() {
        // Arrange
        Set<Recipe> recipeSet = new HashSet<>();
        Recipe recipe = new Recipe();
        recipe.setName("Amaretto Cookie");
        //recipe.setCategories(recipeSet.add());

        recipeSet.add(recipe);

        List<Recipe> expectedRecipes = new ArrayList<>();
        expectedRecipes.add(recipe);

        when(recipeRepository.findAll()).thenReturn(expectedRecipes);

        // Act
        List<Recipe> actualRecipes = recipeController.getAllRecipes();

        // Assert
        assertEquals(expectedRecipes, actualRecipes);
        verify(recipeRepository).findAll();
    }
    @Test
    void addRecipes_ReturnsSuccessfulResponse() {
        // Arrange
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setName("Amaretto Cake");
        /*recipeDto.setCategories(Collections.singletonList("Cakes"));
        recipeDto.setIngredients("Yellow cake mix; no pudding");
        recipeDto.setDirection("abcd");*/

        when(recipeService.addRecipe(recipeDto)).thenReturn("Recipe added successfully");

        // Act
        ResponseDto responseDto = recipeController.addRecipes(recipeDto);

        // Assert
        assertEquals(StatusEnum.SUCCESSFUL, responseDto.getStatus());
        assertEquals("Recipe added successfully", responseDto.getContent());
        verify(recipeService).addRecipe(recipeDto);
    }

    @Test
    void getRecipeByName_WithExistingRecipe_ReturnsRecipe() {
        // Arrange
        String recipeName = "Amaretto Cookie";
        Recipe expectedRecipe = new Recipe(recipeName);

        when(recipeRepository.findByName(recipeName)).thenReturn(Optional.of(expectedRecipe));

        // Act
        ResponseEntity<Recipe> response = recipeController.getRecipeByName(recipeName);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedRecipe, response.getBody());
        verify(recipeRepository).findByName(recipeName);
    }

    @Test
    void getRecipeByName_WithNonExistingRecipe_ReturnsNotFound() {
        // Arrange
        String recipeName = "Non-existing Recipe";

        when(recipeRepository.findByName(recipeName)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Recipe> response = recipeController.getRecipeByName(recipeName);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(recipeRepository).findByName(recipeName);
    }
}
package com.recipes.service;

import com.recipes.dto.RecipeDto;
import com.recipes.exception.RecipeUniqueException;
import com.recipes.exception.RecipeValidationException;
import com.recipes.mapper.RecipeMapper;
import com.recipes.model.*;
import com.recipes.repository.*;
import com.recipes.service.impl.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;

import static com.recipes.constants.RecipeConstants.NAME_MANDATORY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private DirectionRepository directionRepository;
    @Mock
    private StepRepository stepRepository;
    @Mock
    private RecipeMapper recipeMapper;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private IngredientRepository ingredientRepository;
    @Mock
    private IngredientDetailsRepository ingredientDetailsRepository;
    @Mock
    private AmtRepository amtRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(
                recipeRepository, directionRepository, stepRepository, recipeMapper,
                categoryRepository, ingredientRepository, ingredientDetailsRepository, amtRepository
        );
    }

    @Test
    void addRecipe_EmptyName_ThrowsRecipeValidationException() {
        // Arrange
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setName("");

        // Act & Assert
        assertThrows(RecipeValidationException.class, () -> {
            recipeService.addRecipe(recipeDto);
        });
        verify(recipeRepository, never()).save(any());
    }
    
    @Test
    void getAllRecipeByCategory_ValidCategory_ReturnsMatchingRecipes() {
        // Arrange
        String category = "Liquor";
        Recipe recipe1 = new Recipe();
        recipe1.setName("Amaretto Cookie");
        Category category1 = new Category();
        category1.setName(category);
        recipe1.setCategories(Collections.singleton(category1));

        Recipe recipe2 = new Recipe();
        recipe2.setName("Amaretto Cakes");
        Category category2 = new Category();
        category2.setName("Cookie");
        recipe2.setCategories(Collections.singleton(category2));

        Iterable<Recipe> recipes = Arrays.asList(recipe1, recipe2);
        when(recipeRepository.findAll()).thenReturn(recipes);

        // Act
        Set<Recipe> result = recipeService.getAllRecipeByCategory(category);

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.contains(recipe1));
        assertFalse(result.contains(recipe2));
    }

    @Test
    void getAllRecipeByCategory_NoMatchingCategory_ReturnsEmptySet() {
        // Arrange
        String category = "Liquor";
        Recipe recipe1 = new Recipe();
        recipe1.setName("Recipe 1");
        Category category1 = new Category();
        category1.setName("Cookie");
        recipe1.setCategories(Collections.singleton(category1));

        Recipe recipe2 = new Recipe();
        recipe2.setName("Recipe 2");
        Category category2 = new Category();
        category2.setName("Cake mixes");
        recipe2.setCategories(Collections.singleton(category2));

        Iterable<Recipe> recipes = Arrays.asList(recipe1, recipe2);
        when(recipeRepository.findAll()).thenReturn(recipes);

        // Act
        Set<Recipe> result = recipeService.getAllRecipeByCategory(category);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    public void testAddRecipe_NameMissing() {
        // Arrange
        RecipeDto recipeDto = new RecipeDto();

        // Act and Assert
        assertThrows(RecipeValidationException.class, () -> {
            recipeService.addRecipe(recipeDto);
        });
    }
}
package com.recipes.service.impl;

import com.recipes.dto.RecipeDto;
import com.recipes.exception.RecipeUniqueException;
import com.recipes.exception.RecipeValidationException;
import com.recipes.mapper.RecipeMapper;
import com.recipes.model.*;
import com.recipes.repository.*;
import com.recipes.service.RecipeService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.recipes.constants.RecipeConstants.NAME_MANDATORY;

@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;
    private DirectionRepository directionRepository;
    private StepRepository stepRepository;
    private RecipeMapper recipeMapper;
    private CategoryRepository categoryRepository;
    private IngredientRepository ingredientRepository;
    private IngredientDetailsRepository ingredientDetailsRepository;
    private AmtRepository amtRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository, DirectionRepository directionRepository, StepRepository stepRepository, RecipeMapper recipeMapper, CategoryRepository categoryRepository, IngredientRepository ingredientRepository, IngredientDetailsRepository ingredientDetailsRepository, AmtRepository amtRepository) {
        this.recipeRepository = recipeRepository;
        this.directionRepository = directionRepository;
        this.stepRepository = stepRepository;
        this.recipeMapper = recipeMapper;
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
        this.ingredientDetailsRepository = ingredientDetailsRepository;
        this.amtRepository = amtRepository;
    }
    @Override
    public String addRecipe(RecipeDto recipe) {
       if(Objects.isNull(recipe.getName()) || recipe.getName().isEmpty()) {
           throw new RecipeValidationException(NAME_MANDATORY);
       }
        try {
            Recipe recipeModel = recipeMapper.mapRecipe(recipe);
            /*if (recipeRepository.existsById(recipeModel.getId())){
                throw new RecipeUniqueException("Cannot add duplicate Recipe");
            }*/
            Set<Category> categories = recipeModel.getCategories();
            List<Ingredient> ingredients = recipeModel.getIngredients();
            persistIngredients(ingredients);
            ingredientRepository.saveAll(ingredients);
            Set<Category> categorySet = persistCategories(categories);
            Direction direction = recipeModel.getDirection();
            persistRecipe(recipeModel, categorySet, ingredients, direction);
        }catch (DataIntegrityViolationException e){
                throw new RecipeUniqueException("Can not be added duplicate Recipe");
        }
        return "Recipe added successfully";
    }
    public void persistRecipe(Recipe recipeModel, Set<Category> updatedCategories, List<Ingredient> updateIngredient, Direction direction) {
        persistStepsAndDirection(direction);
        recipeModel.setDirection(direction);
        recipeModel.setIngredients(updateIngredient);
        recipeModel.setCategories(updatedCategories);
        recipeRepository.save(recipeModel);
    }
    public void persistStepsAndDirection(Direction direction) {
        List<Step> steps = direction.getSteps();
        stepRepository.saveAll(steps);
        direction.setSteps(steps);
        directionRepository.save(direction);
    }
    public Set<Category> persistCategories(Set<Category> categories) {
        Set<Category> categoryList = new HashSet<>();
        for (Category category : categories) {
            Optional<Category> category1 = categoryRepository.findByName(category.getName());
            if (category1.isPresent()) {
                categoryList.add(category1.get());
            } else {
                categoryRepository.save(category);
                categoryList.add(category);
            }
        }
        return categoryList;
    }
    public void persistIngredients(List<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            List<IngredientDetails> ingDetails = ingredient.getIngredientDetails();
            for (IngredientDetails ingredientDetails : ingDetails) {
                amtRepository.save(ingredientDetails.getAmt());
            }
            ingredientDetailsRepository.saveAll(ingDetails);
        }
    }
    @Override
    public Set<Recipe> getAllRecipeByCategory(final String category){
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Set<Recipe> recipeSet = new HashSet<>();
       for(Recipe recipe:recipes){
           Optional<Category> categoryOptional = recipe.getCategories().stream().filter(category1 -> category1.getName().equals(category)).findFirst();
           if(categoryOptional.isPresent()){
               recipeSet.add(recipe);
           }
       }
       return recipeSet;
    }
}

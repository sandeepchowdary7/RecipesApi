package com.recipes.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipes.dto.RecipeDto;
import com.recipes.model.Recipe;
import org.springframework.stereotype.Component;

@Component
public class RecipeMapper {

    public Recipe mapRecipe(RecipeDto recipeDto){
        ObjectMapper ob = new ObjectMapper();
        return ob.convertValue(recipeDto, Recipe.class);
    }
}

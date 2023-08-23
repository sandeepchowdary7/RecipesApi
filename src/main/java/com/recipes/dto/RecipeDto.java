package com.recipes.dto;


import com.recipes.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class RecipeDto {
    private String name;
    private List<IngredientDto> ingredients;
    private String yield;
    private List<CategoryDto> categories;
    private DirectionDto direction;
}

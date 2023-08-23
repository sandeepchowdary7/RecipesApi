package com.recipes.dto;

import lombok.Data;

import java.util.List;

@Data

public class IngredientDto {
    private String name;
    private List<IngredientDetailsDto> ingredientDetails;
}

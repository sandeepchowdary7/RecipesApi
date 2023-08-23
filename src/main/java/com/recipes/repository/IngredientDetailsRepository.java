package com.recipes.repository;

import com.recipes.model.Ingredient;
import com.recipes.model.IngredientDetails;
import org.springframework.data.repository.CrudRepository;

public interface IngredientDetailsRepository extends CrudRepository<IngredientDetails, Long> {
}

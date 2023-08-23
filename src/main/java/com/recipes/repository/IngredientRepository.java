package com.recipes.repository;

import com.recipes.model.Category;
import com.recipes.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}

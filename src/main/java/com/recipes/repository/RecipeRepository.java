package com.recipes.repository;

import com.recipes.model.Category;
import com.recipes.model.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    @Query(value = "select * from recipe where name =:name", nativeQuery =true)
    Optional<Recipe> findByName(String name);

}
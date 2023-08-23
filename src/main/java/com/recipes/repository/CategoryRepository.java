package com.recipes.repository;

import com.recipes.model.Category;
import com.recipes.model.Step;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
    @Query(value = "select * from category where name =:name", nativeQuery =true)
    Optional<Category> findByName(String name);
}

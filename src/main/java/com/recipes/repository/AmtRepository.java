package com.recipes.repository;

import com.recipes.model.Amt;
import com.recipes.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface AmtRepository extends CrudRepository<Amt, Long> {
}

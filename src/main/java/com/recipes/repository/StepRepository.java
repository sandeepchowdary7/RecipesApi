package com.recipes.repository;

import com.recipes.model.Direction;
import com.recipes.model.Step;
import org.springframework.data.repository.CrudRepository;

public interface StepRepository extends CrudRepository<Step, Long> {
}

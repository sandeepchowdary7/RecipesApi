package com.recipes.dto;

import com.recipes.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
public class StepDto {
    private String step;
}

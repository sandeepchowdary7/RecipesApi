package com.recipes.dto;

import com.recipes.model.Step;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Data
public class DirectionDto {
    private List<Step> steps;
}

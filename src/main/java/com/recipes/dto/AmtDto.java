package com.recipes.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
public class AmtDto {
    private String qty;
    private String unit;
}

package com.recipes.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table(name = "amt")
@Entity
public class Amt extends BaseEntity {
    private String qty;
    private String unit;
}

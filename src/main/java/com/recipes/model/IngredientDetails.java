package com.recipes.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Table(name = "ingredientdetails")
@Entity
public class IngredientDetails extends BaseEntity{
    @OneToOne
    private Amt amt;
    private String item;
}

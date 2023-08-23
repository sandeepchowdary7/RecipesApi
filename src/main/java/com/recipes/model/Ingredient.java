package com.recipes.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Table(name = "ingredient")
@Entity
public class Ingredient extends BaseEntity{

    private String name;
    @OneToMany
    private List<IngredientDetails> ingredientDetails;

}

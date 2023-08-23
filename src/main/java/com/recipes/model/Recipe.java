package com.recipes.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Table(name = "recipe")
@Entity
@Getter
@Setter
public class Recipe extends BaseEntity {
    @Column(unique = true)
    private String name;
    @OneToMany(orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;
    private String yield;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Category> categories;
    @OneToOne(orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Direction direction;

    public Recipe(String s) {
    }

    public Recipe() {

    }
}

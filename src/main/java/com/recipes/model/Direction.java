package com.recipes.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Table(name = "direction")
@Entity
public class Direction extends BaseEntity {
    @OneToMany
    private List<Step> steps;
}

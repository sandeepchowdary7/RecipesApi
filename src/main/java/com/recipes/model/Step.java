package com.recipes.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table(name = "step")
@Entity
public class Step extends BaseEntity {
    private String step;
}

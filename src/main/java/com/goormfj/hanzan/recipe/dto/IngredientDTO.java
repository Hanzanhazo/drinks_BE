package com.goormfj.hanzan.recipe.dto;

import lombok.Data;
import jakarta.persistence.Embeddable;

@Data
@Embeddable
public class IngredientDTO {
    private long id;
    private String material;
    private String quantity;
}

package com.goormfj.hanzan.recipe.dto;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class StepDTO {
    private long id;
    private String description;
    private String imageUrl;
}

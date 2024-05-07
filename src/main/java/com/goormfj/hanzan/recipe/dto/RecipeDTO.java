package com.goormfj.hanzan.recipe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RecipeDTO {
    private Long id;

    @NotBlank(message = "Name cannot be empty.")
    private String name;

    @Size(min = 10, message = "Description must be at least 10 characters.")
    private String description;

    @NotBlank(message = "Type cannot be empty.")
    private TypeDTO types;

    @NotEmpty(message = "Ingredients list cannot be empty.")
    private List<IngredientDTO> ingredientDTOS = new ArrayList<>();

    public List<IngredientDTO> getIngredients() {
        return ingredientDTOS;
    }

    public void setIngredients(List<IngredientDTO> ingredientDTOS) {
        this.ingredientDTOS = ingredientDTOS;
    }

    @NotEmpty(message = "Steps list cannot be empty.")
    private List<StepDTO> steps;

    private String recommendationReason;
    private int likes;
    private int dislikes;
    private List<String> tags;
    private List<CommentDTO> comments;

    private String mainImageUrl;
    private String videoUrl;
    private boolean isPublic;

    }


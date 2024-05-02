package com.goormfj.hanzan.recipe.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
public class CommentDTO {
    private Long id;

    @NotBlank(message = "Comment text cannot be empty.")
    private String text;

    @NotBlank(message = "Author cannot be empty.")
    private String author;

    private RecipeDTO recipe; // Relation to Recipe
    private CommentDTO parentComment; // For threaded comments
    private List<CommentDTO> replies;
}

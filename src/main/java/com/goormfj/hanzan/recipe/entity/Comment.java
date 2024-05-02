package com.goormfj.hanzan.recipe.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id") // 부모 댓글을 위한 참조
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<Comment> replies = new ArrayList<>();

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}

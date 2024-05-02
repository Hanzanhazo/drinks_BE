package com.goormfj.hanzan.recipe.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String type;

    @ElementCollection
    private List<String> ingredients = new ArrayList<>();

    @ElementCollection
    private List<String> steps = new ArrayList<>();

    @ElementCollection
    private List<String> tags = new ArrayList<>(); // 태그 리스트

    private String recommendationReason;
    private int likes;
    private int dislikes;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    private String imageUrl;
    private String videoUrl;
    private boolean isPublic;

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setRecipe(this);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void incrementLikes() {
        this.likes++;
    }

    public void incrementDislikes() {
        this.dislikes++;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}

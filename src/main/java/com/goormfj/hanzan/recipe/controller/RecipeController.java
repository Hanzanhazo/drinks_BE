package com.goormfj.hanzan.recipe.controller;

import com.goormfj.hanzan.recipe.dto.RecipeDTO;
import com.goormfj.hanzan.recipe.dto.CommentDTO;
import com.goormfj.hanzan.recipe.service.RecipeService;
import com.goormfj.hanzan.recipe.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private LikeService likeService;

    @PutMapping("/{id}")
    public ResponseEntity<RecipeDTO> updateRecipe(@PathVariable Long id, @RequestBody RecipeDTO recipeDTO) {
        RecipeDTO updatedRecipe = recipeService.updateRecipe(id, recipeDTO);
        return ResponseEntity.ok(updatedRecipe);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipeDetails(@PathVariable Long id) {
        RecipeDTO recipe = recipeService.getRecipeDetails(id);
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/like/{id}")
    public ResponseEntity<Void> likeRecipe(@PathVariable Long id) {
        likeService.likeRecipe(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/dislike/{id}")
    public ResponseEntity<Void> dislikeRecipe(@PathVariable Long id) {
        likeService.dislikeRecipe(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/popular")
    public ResponseEntity<List<RecipeDTO>> getPopularRecipes() {
        List<RecipeDTO> popularRecipes = recipeService.getPopularRecipes();
        return ResponseEntity.ok(popularRecipes);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<Page<RecipeDTO>> getRecipesByType(@PathVariable String type, Pageable pageable) {
        Page<RecipeDTO> page = recipeService.getRecipesByType(type, pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping("/{recipeId}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable Long recipeId, @RequestBody CommentDTO commentDTO) {
        CommentDTO savedComment = recipeService.addComment(recipeId, commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }

    @GetMapping("/{recipeId}/comments")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable Long recipeId) {
        List<CommentDTO> comments = recipeService.getComments(recipeId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/")
    public ResponseEntity<RecipeDTO> createRecipe(@RequestBody RecipeDTO recipeDTO) {
        RecipeDTO newRecipe = recipeService.createRecipe(recipeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRecipe);
    }

    @GetMapping("/")
    public ResponseEntity<Page<RecipeDTO>> getAllRecipes(Pageable pageable) {
        Page<RecipeDTO> page = recipeService.getRecipes(pageable);
        return ResponseEntity.ok(page);
    }

    @PatchMapping("/{id}/tags")
    public ResponseEntity<RecipeDTO> updateRecipeTags(@PathVariable Long id, @RequestBody List<String> tags) {
        RecipeDTO recipe = recipeService.updateRecipeTags(id, tags);
        return ResponseEntity.ok(recipe);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<RecipeDTO>> getRecipesByTags(@RequestBody List<String> tags, Pageable pageable) {
        Page<RecipeDTO> recipes = recipeService.findRecipesByTags(tags, pageable);
        return ResponseEntity.ok(recipes);
    }

    @PatchMapping("/{id}/visibility")
    public ResponseEntity<Void> updateRecipeVisibility(@PathVariable Long id, @RequestBody boolean isPublic) {
        recipeService.updateRecipeVisibility(id, isPublic);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/categories")
    public ResponseEntity<String> createCategory(@RequestBody String category) {
        String newCategory = recipeService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        recipeService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}

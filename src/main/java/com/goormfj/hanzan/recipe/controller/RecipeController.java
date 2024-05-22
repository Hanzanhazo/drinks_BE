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
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private LikeService likeService;

    // Recipe Management
    @PostMapping("/recipes/create")
    public ResponseEntity<RecipeDTO> createRecipe(@RequestBody RecipeDTO recipeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.createRecipe(recipeDTO));
    }

    @GetMapping("/recipes/details/{id}")
    public ResponseEntity<RecipeDTO> getRecipeDetails(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getRecipeDetails(id));
    }

    @PutMapping("/recipes/update/{id}")
    public ResponseEntity<RecipeDTO> updateRecipe(@PathVariable Long id, @RequestBody RecipeDTO recipeDTO) {
        return ResponseEntity.ok(recipeService.updateRecipe(id, recipeDTO));
    }

    @DeleteMapping("/recipes/delete/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.ok().build();
    }

    // Like and Dislike
    @PostMapping("/recipes/{id}/like")
    public ResponseEntity<Void> likeRecipe(@PathVariable Long id) {
        likeService.likeRecipe(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/recipes/{id}/dislike")
    public ResponseEntity<Void> dislikeRecipe(@PathVariable Long id) {
        likeService.dislikeRecipe(id);
        return ResponseEntity.ok().build();
    }

    // Recipe Search and Listing
    @GetMapping("/recipes/popular")
    public ResponseEntity<List<RecipeDTO>> getPopularRecipes() {
        return ResponseEntity.ok(recipeService.getPopularRecipes());
    }

    @GetMapping("/recipes/type/{type}")
    public ResponseEntity<Page<RecipeDTO>> getRecipesByType(@PathVariable String type, Pageable pageable) {
        return ResponseEntity.ok(recipeService.getRecipesByType(type, pageable));
    }

    @GetMapping("/recipes/search")
    public ResponseEntity<Page<RecipeDTO>> getRecipesByTags(@RequestBody List<String> tags, Pageable pageable) {
        return ResponseEntity.ok(recipeService.findRecipesByTags(tags, pageable));
    }

    // Comments Management
    @PostMapping("/recipes/{recipeId}/comments/add")
    public ResponseEntity<CommentDTO> addComment(@PathVariable Long recipeId, @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.addComment(recipeId, commentDTO));
    }

    @GetMapping("/recipes/{recipeId}/comments/view")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable Long recipeId) {
        return ResponseEntity.ok(recipeService.getComments(recipeId));
    }

    // Tags and Visibility
    @PatchMapping("/recipes/{id}/tags/update")
    public ResponseEntity<RecipeDTO> updateRecipeTags(@PathVariable Long id, @RequestBody List<String> tags) {
        return ResponseEntity.ok(recipeService.updateRecipeTags(id, tags));
    }

    @PatchMapping("/recipes/{id}/visibility/update")
    public ResponseEntity<Void> updateRecipeVisibility(@PathVariable Long id, @RequestBody boolean isPublic) {
        recipeService.updateRecipeVisibility(id, isPublic);
        return ResponseEntity.ok().build();
    }

    // Category Management
    @PostMapping("/categories/create")
    public ResponseEntity<String> createCategory(@RequestBody String category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.createCategory(category));
    }

    @DeleteMapping("/categories/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        recipeService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}

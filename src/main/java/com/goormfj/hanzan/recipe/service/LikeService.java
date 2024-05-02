package com.goormfj.hanzan.recipe.service;

import com.goormfj.hanzan.recipe.entity.Recipe;
import com.goormfj.hanzan.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    private RecipeRepository recipeRepository;

    public void likeRecipe(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found"));
        recipe.incrementLikes();
        recipeRepository.save(recipe);
    }

    public void dislikeRecipe(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found"));
        recipe.incrementDislikes();
        recipeRepository.save(recipe);
    }
}

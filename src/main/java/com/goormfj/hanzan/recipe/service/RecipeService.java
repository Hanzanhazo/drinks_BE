package com.goormfj.hanzan.recipe.service;

import com.goormfj.hanzan.recipe.dto.CommentDTO;
import com.goormfj.hanzan.recipe.dto.RecipeDTO;
import com.goormfj.hanzan.recipe.entity.Recipe;
import com.goormfj.hanzan.recipe.entity.Comment;  // Comment 엔티티를 추가로 임포트
import com.goormfj.hanzan.recipe.exception.EntityNotFoundException;
import com.goormfj.hanzan.recipe.mapper.RecipeMapper;
import com.goormfj.hanzan.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeMapper recipeMapper;

    public RecipeDTO createRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = recipeMapper.recipeDTOToRecipe(recipeDTO);
        recipeRepository.save(recipe);
        return recipeMapper.recipeToRecipeDTO(recipe);
    }

    public RecipeDTO getRecipeDetails(Long id) {
        Recipe recipe = findRecipeById(id);
        return recipeMapper.recipeToRecipeDTO(recipe);
    }

    public RecipeDTO updateRecipe(Long id, RecipeDTO recipeDTO) {
        Recipe existingRecipe = findRecipeById(id);
        updateRecipeDetails(existingRecipe, recipeDTO);
        recipeRepository.save(existingRecipe);
        return recipeMapper.recipeToRecipeDTO(existingRecipe);
    }

    public void deleteRecipe(Long id) {
        Recipe recipe = findRecipeById(id);
        recipeRepository.delete(recipe);
    }

    public List<RecipeDTO> getPopularRecipes() {
        return recipeRepository.findAllByOrderByLikesDesc()
                .stream()
                .map(recipeMapper::recipeToRecipeDTO)
                .collect(Collectors.toList());
    }

    public CommentDTO addComment(Long recipeId, CommentDTO commentDTO) {
        Recipe recipe = findRecipeById(recipeId);
        Comment comment = recipeMapper.commentDTOToComment(commentDTO);
        recipe.addComment(comment);
        recipeRepository.save(recipe);
        return recipeMapper.commentToCommentDTO(comment);
    }

    public List<CommentDTO> getComments(Long recipeId) {
        Recipe recipe = findRecipeById(recipeId);
        return recipe.getComments()
                .stream()
                .map(recipeMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }

    public Page<RecipeDTO> getRecipes(Pageable pageable) {
        return recipeRepository.findAll(pageable)
                .map(recipeMapper::recipeToRecipeDTO);
    }

    public Page<RecipeDTO> getRecipesByType(String type, Pageable pageable) {
        return recipeRepository.findByTypeOrderByLikesDesc(type, pageable)
                .map(recipeMapper::recipeToRecipeDTO);
    }

    public Page<RecipeDTO> findRecipesByTags(List<String> tags, Pageable pageable) {
        return recipeRepository.findByTagsIn(tags, pageable)
                .map(recipeMapper::recipeToRecipeDTO);
    }

    public RecipeDTO updateRecipeTags(Long id, List<String> tags) {
        Recipe recipe = findRecipeById(id);
        if (recipe != null) {
            recipe.setTags(tags);
            recipeRepository.save(recipe);
            return recipeMapper.recipeToRecipeDTO(recipe);
        } else {
            throw new EntityNotFoundException("Recipe not found with id: " + id);
        }
    }

    public void updateRecipeVisibility(Long id, boolean isPublic) {
        Recipe recipe = findRecipeById(id);
        recipe.setPublic(isPublic);
        recipeRepository.save(recipe);
    }

    public String createCategory(String category) {
        // Implement category creation logic here
        return "Category created: " + category;
    }

    public void deleteCategory(Long id) {
        recipeRepository.deleteById(id);
    }

    private Recipe findRecipeById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recipe not found with id: " + id));
    }

    private void updateRecipeDetails(Recipe recipe, RecipeDTO recipeDTO) {
        recipe.setName(recipeDTO.getName());
        recipe.setDescription(recipeDTO.getDescription());
        recipe.setType(recipeDTO.getType());
        recipe.setIngredients(recipeDTO.getIngredients());
        recipe.setSteps(recipeDTO.getSteps());
        recipe.setRecommendationReason(recipeDTO.getRecommendationReason());
        recipe.setTags(recipeDTO.getTags());
        recipe.setPublic(recipeDTO.isPublic());
    }
}

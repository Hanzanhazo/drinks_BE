package com.goormfj.hanzan.recipe.mapper;

import com.goormfj.hanzan.recipe.dto.CommentDTO;
import com.goormfj.hanzan.recipe.dto.RecipeDTO;
import com.goormfj.hanzan.recipe.entity.Comment;
import com.goormfj.hanzan.recipe.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    @Mapping(target = "recommendationReason", source = "recommendationReason")
    RecipeDTO recipeToRecipeDTO(Recipe recipe);

    @Mapping(target = "recommendationReason", source = "recommendationReason")
    Recipe recipeDTOToRecipe(RecipeDTO recipeDTO);

    @Mapping(target = "parentComment", source = "parentComment")
    @Mapping(target = "replies", source = "replies")
    CommentDTO commentToCommentDTO(Comment comment);

    @Mapping(target = "parentComment", source = "parentComment")
    @Mapping(target = "replies", source = "replies")
    Comment commentDTOToComment(CommentDTO commentDTO);

    List<CommentDTO> mapComments(List<Comment> comments);
    List<Comment> mapCommentDTOs(List<CommentDTO> commentDTOs);
}

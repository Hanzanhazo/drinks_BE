package com.goormfj.hanzan.recipe.mapper;

import com.goormfj.hanzan.recipe.dto.CommentDTO;
import com.goormfj.hanzan.recipe.dto.IngredientDTO;
import com.goormfj.hanzan.recipe.dto.RecipeDTO;
import com.goormfj.hanzan.recipe.dto.StepDTO;
import com.goormfj.hanzan.recipe.dto.TypeDTO;
import com.goormfj.hanzan.recipe.entity.Comment;
import com.goormfj.hanzan.recipe.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.lang.reflect.Type;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    @Mapping(target = "types", source = "type")
    RecipeDTO recipeToRecipeDTO(Recipe recipe);

    @Mapping(target = "type", source = "types")
    Recipe recipeDTOToRecipe(RecipeDTO recipeDTO);

    List<CommentDTO> mapComments(List<Comment> comments);
    List<Comment> mapCommentDTOs(List<CommentDTO> commentDTOs);


    default List<IngredientDTO> mapIngredients(List<String> ingredients) {
        return ingredients.stream()
                .map(ingredient -> {
                    IngredientDTO dto = new IngredientDTO();
                    dto.setValue(ingredient);
                    return dto;
                }).collect(Collectors.toList());
    }

    default List<String> mapIngredientDTOs(List<IngredientDTO> ingredientDTOS) {
        return ingredientDTOS.stream()
                .map(IngredientDTO::getValue)
                .collect(Collectors.toList());
    }

    default TypeDTO stringToTypeDTO(String type) {
        TypeDTO typeDTO = new TypeDTO();
        typeDTO.setSojuType(type.contains("soju"));
        typeDTO.setBeerType(type.contains("beer"));
        typeDTO.setWineType(type.contains("wine"));
        return typeDTO;
    }

    default String typeDTOToString(TypeDTO typeDTO) {
        StringBuilder typeBuilder = new StringBuilder();
        if (typeDTO.isSojuType()) typeBuilder.append("soju ");
        if (typeDTO.isBeerType()) typeBuilder.append("beer ");
        if (typeDTO.isWineType()) typeBuilder.append("wind ");
        return typeBuilder.toString().trim();
    }

    @Mapping(target = "parentComment", source = "parentComment")
    @Mapping(target = "replies", source = "replies")
    CommentDTO commentToCommentDTO(Comment comment);

    @Mapping(target = "parentComment", source = "parentComment")
    @Mapping(target = "replies", source = "replies")
    Comment commentDTOToComment(CommentDTO commentDTO);

}

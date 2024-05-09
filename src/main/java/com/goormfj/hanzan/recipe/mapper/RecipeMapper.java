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
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    @Mapping(target = "types", source = "type")
    @Mapping(target = "ingredientDTOS", source = "ingredients")
    RecipeDTO recipeToRecipeDTO(Recipe recipe);

    @Mapping(target = "type", source = "types")
    @Mapping(target = "ingredients", source = "ingredientDTOS")
    Recipe recipeDTOToRecipe(RecipeDTO recipeDTO);

    List<CommentDTO> mapComments(List<Comment> comments);
    List<Comment> mapCommentDTOs(List<CommentDTO> commentDTOs);


    default List<IngredientDTO> mapIngredients(List<IngredientDTO> IngredientDTOs) {
        return IngredientDTOs.stream()
                .map(ingredient -> {
                    IngredientDTO dto = new IngredientDTO();
                    dto.setId(ingredient.getId());
                    dto.setMaterial(ingredient.getMaterial());
                    dto.setQuantity(ingredient.getQuantity());
                    return dto;
                }).collect(Collectors.toList());
    }

//    default List<IngredientDTO> mapIngredientDTOs(List<IngredientDTO> ingredientDTOS) {
//        return ingredientDTOS.stream()
//                .map(dto -> {
//                    IngredientDTO newDto = new IngredientDTO();
//                    newDto.setId(dto.getId());
//                    newDto.setMaterial(dto.getMaterial());
//                    newDto.setQuantity(dto.getQuantity());
//                    return newDto;
//                }).collect(Collectors.toList());
//    }

    default TypeDTO stringToTypeDTO(String type) {
        TypeDTO typeDTO = new TypeDTO();
        typeDTO.setSojuType(type.contains("soju"));
        typeDTO.setBeerType(type.contains("beer"));
        typeDTO.setWineType(type.contains("wine"));
        typeDTO.setSakeType(type.contains("sake"));
        typeDTO.setVodkaType(type.contains("vodka"));
        typeDTO.setWhiskeyType(type.contains("whiskey"));
        typeDTO.setMakgeolliType(type.contains("makgeolli"));
        return typeDTO;
    }

    default String typeDTOToString(TypeDTO typeDTO) {
        StringBuilder typeBuilder = new StringBuilder();
        if (typeDTO.isSojuType()) typeBuilder.append("soju ");
        if (typeDTO.isBeerType()) typeBuilder.append("beer ");
        if (typeDTO.isWineType()) typeBuilder.append("wine ");
        if (typeDTO.isSakeType()) typeBuilder.append("sake ");
        if (typeDTO.isVodkaType()) typeBuilder.append("vodka ");
        if (typeDTO.isWhiskeyType()) typeBuilder.append("whiskey ");
        if (typeDTO.isMakgeolliType()) typeBuilder.append("makgeolli");
        return typeBuilder.toString().trim();
    }

    @Mapping(target = "parentComment", source = "parentComment")
    @Mapping(target = "replies", source = "replies")
    CommentDTO commentToCommentDTO(Comment comment);

    @Mapping(target = "parentComment", source = "parentComment")
    @Mapping(target = "replies", source = "replies")
    Comment commentDTOToComment(CommentDTO commentDTO);

}

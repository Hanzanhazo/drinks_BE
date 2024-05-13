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

    default CommentDTO commentToCommentDTO(Comment comment) {
        return commentToCommentDTO(comment, 0);
    }

    default CommentDTO commentToCommentDTO(Comment comment, int depth) {
        if (comment == null || depth > 3) {
            return null;
        }

        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setText(comment.getText());
        dto.setAuthor(comment.getAuthor());
//        dto.setRecipe(comment.getRecipe() != null ? recipeToRecipeDTO(comment.getRecipe()) : null);

//        if (comment.getParentComment() !=null) {
//            dto.setParentComment(commentToCommentDTO(comment.getParentComment(), depth + 1));
//        }

        if (comment.getReplies() != null && !comment.getReplies().isEmpty()) {
            dto.setReplies(comment.getReplies().stream()
                    .map(reply -> commentToCommentDTO(reply, depth + 1))
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    default Comment commentDTOToComment(CommentDTO commentDTO) {
        if (commentDTO == null) {
            return null;
        }

        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setText(commentDTO.getText());
        comment.setAuthor(commentDTO.getAuthor());

        return comment;
    }

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
        if (typeDTO == null) {
            return "";
        }

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

//    @Mapping(target = "parentComment", source = "parentComment")
//    @Mapping(target = "replies", source = "replies")
//    CommentDTO commentToCommentDTO(Comment comment);
//
//    @Mapping(target = "parentComment", source = "parentComment")
//    @Mapping(target = "replies", source = "replies")
//    Comment commentDTOToComment(CommentDTO commentDTO);

}

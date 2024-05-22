package com.goormfj.hanzan.recipe.dto;

import lombok.Data;
import java.util.List;

@Data
public class AlcoholDTO {

    private Long id;
    private String name;
    private String description;
    private String type;
    private double volume;
    private double alcoholContent;
    private String country;
    private String imageUrl;
}

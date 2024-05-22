package com.goormfj.hanzan.recipe.entity;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
public class Alcohol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String description;
    private String type;
    private double volume;
    private double alcoholContent;
    private String country;
    private String imageUrl;
}

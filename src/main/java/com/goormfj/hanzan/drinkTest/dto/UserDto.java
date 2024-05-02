package com.goormfj.hanzan.drinkTest.dto;

import com.goormfj.hanzan.drinkTest.domain.DrinkPreferenceType;
import lombok.Getter;

@Getter
public class UserDto {
    private final Long id;
    private final String name;
    private final String email;
    private final DrinkPreferenceType drinkPreferenceType;

    public UserDto(Long id, String name, String email, DrinkPreferenceType drinkPreferenceType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.drinkPreferenceType = drinkPreferenceType;
    }

}

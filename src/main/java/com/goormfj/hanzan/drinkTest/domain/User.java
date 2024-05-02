package com.goormfj.hanzan.drinkTest.domain;

import jakarta.persistence.Id;
import lombok.Getter;


@Getter
public class User {
    @Id//머지 후 로그인 user entity사용
    private final Long userId;
    private final String userName;
    private final String userEmail;
    private final String encryptedPassword;
    private final DrinkPreferenceType drinkPreferenceType;

    public User(Long userId, String userName, String userEmail, String encryptedPassword, DrinkPreferenceType drinkPreferenceType) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.encryptedPassword = encryptedPassword;
        this.drinkPreferenceType = drinkPreferenceType;
    }

}

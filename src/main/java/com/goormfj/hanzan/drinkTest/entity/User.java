package com.goormfj.hanzan.drinkTest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

public class User {//머지 후 로그인 user entity사용
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long userId;

    @Column(nullable = false, unique = true)
    private final String username;

    @Column(nullable = false)
    private final String password;

    @Column(nullable = false, unique = true)
    private final String email;

    // JPA를 위한 기본 생성자는 private으로 숨겨야 합니다.
    public User() {
        this.drinkPreferenceType = drinkPreferenceType;
        this.userId = null;
        this.username = null;
        this.password = null;
        this.email = null;
    }

    @Setter
    @Getter
    private DrinkPreferenceType drinkPreferenceType;

}

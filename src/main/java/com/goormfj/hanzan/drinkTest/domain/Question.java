package com.goormfj.hanzan.drinkTest.domain;

import lombok.Getter;

@Getter
public enum Question {
    FLAVOR_PREFERENCE("맛의 선호도"),
    ALCOHOL_LEVEL("도수 선택"),
    SITUATION("상황 가정"),
    DRAMA_OR_MOVIE_SCENE("드라마나 영화의 한 장면");

    private final String description;

    Question(String description) {
        this.description = description;
    }

}

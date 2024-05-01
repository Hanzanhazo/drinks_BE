package com.goormfj.hanzan.drinkTest.entity;

public enum DrinkPreferenceType {
    TYPE_1("모두에게 사랑받는 아이스박스"),
    TYPE_2("치고 나가는 스포츠카"),
    TYPE_3("궁금한게 많은 명탐정"),
    TYPE_4("인생은 나홀로! 철학자"),
    TYPE_5("흥미진진한 진행 MC"),
    TYPE_6("다 챙겨주는 츤데레"),
    TYPE_7("자유로운 영혼 로커"),
    TYPE_8("어느 모임이든 사수하는 파티 킬러");

    private final String description;//변경X

    DrinkPreferenceType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

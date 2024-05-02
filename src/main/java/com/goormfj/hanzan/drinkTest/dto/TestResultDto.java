package com.goormfj.hanzan.drinkTest.dto;

import com.goormfj.hanzan.drinkTest.domain.DrinkPreferenceType;
import com.goormfj.hanzan.drinkTest.domain.Question;
import com.goormfj.hanzan.drinkTest.domain.Response;
import lombok.Builder;
import lombok.Getter;


import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Getter
public class TestResultDto {
    private Long userId;
    private Map<Question, Response> responses;
    private DrinkPreferenceType drinkPreferenceType;

    public TestResultDto(Long userId, Map<Question, Response> responses, DrinkPreferenceType drinkPreferenceType) {
        this.userId = userId;
        this.responses = Optional.ofNullable(responses).map(Collections::unmodifiableMap).orElse(Collections.emptyMap());
        this.drinkPreferenceType = drinkPreferenceType;
    }

    TestResultDto testResultDto = new TestResultDto(userId, responses, drinkPreferenceType);
}

package com.goormfj.hanzan.drinkTest.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@NoArgsConstructor
@Builder
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;
    private Long userId;

    @ElementCollection(fetch = FetchType.LAZY)  // 응답 맵을 엔터티 컬렉션으로 저장
    @CollectionTable(name = "test_result_responses", joinColumns = @JoinColumn(name = "result_id"))
    @MapKeyColumn(name = "question")
    @Column(name = "response")
    private Map<Question, Response> responses;

    @Enumerated(EnumType.STRING)
    private DrinkPreferenceType preferenceType;

    public TestResult(Long resultId, Long userId, Map<Question, Response> responses, DrinkPreferenceType preferenceType) {
        this.resultId = resultId;
        this.userId = userId;
        this.responses = Collections.unmodifiableMap(new HashMap<>(responses));
        this.preferenceType = preferenceType;
    }


}


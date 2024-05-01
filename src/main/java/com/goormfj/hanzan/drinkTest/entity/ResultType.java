package com.goormfj.hanzan.drinkTest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

public class ResultType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long resultId;

    @Column(nullable = false)
    private final String resultName;

    @Column(nullable = false)
    private final String typeDescription;

    @Column(nullable = false)
    private final String recommendedAlcohol;

    protected ResultType() {
        this.resultId = null;
        this.resultName = null;
        this.typeDescription = null;
        this.recommendedAlcohol = null;
    }
}

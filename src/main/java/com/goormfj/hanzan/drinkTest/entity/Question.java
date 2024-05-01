package com.goormfj.hanzan.drinkTest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long questionId;

    @Column(nullable = false)
    private final String questionContent;//질문 내용

    @Column(nullable = false)
    private final String options;//질문에 대한 선택지

    protected Question() {
        this.questionId = null;
        this.questionContent = null;
        this.options = null;
    }
}

package com.goormfj.hanzan.drinkTest.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long answerId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private final User user;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private final Question question;

    @Column(nullable = false)
    private final String selectedOption;

    protected Answer() {
        this.answerId = null;
        this.user = null;
        this.question = null;
        this.selectedOption = null;
    }
}

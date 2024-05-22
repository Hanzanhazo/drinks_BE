package com.example.demo;

import com.example.demo.entity.Answer;
import com.example.demo.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @GetMapping("/answers")
    public List<Answer> getAllAnswers() {
        return answerService.getAllAnswers();
    }
}

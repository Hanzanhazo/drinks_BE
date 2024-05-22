package com.example.demo;

import com.example.demo.entity.Question;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String showQuestionForm(Model model) {
        model.addAttribute("question", new Question());
        return "question-form";
    }

    @PostMapping("/submit-question")
    public String submitQuestion(Question question, Model model) {
        questionService.saveQuestion(question);
        model.addAttribute("answers", questionService.getAllAnswers());
        return "result-page";
    }
}

package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.services.QuestionService;
import com.example.demo.model.Question;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class QuestionController {


private final QuestionService questionService;

@Autowired
public QuestionController(QuestionService questionService) {
    this.questionService = questionService;
}

    @GetMapping("/allquestions")
    public List<Question> allQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public List<Question> getAllQuestionsByCategory(@PathVariable String category)
    {
        return questionService.getAllQuestionsByCategory(category);
    }
}

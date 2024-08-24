package com.example.demo.controller;


import com.example.demo.model.QuestionWrapper;
import com.example.demo.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Response;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService){
        this.quizService = quizService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category, numQ, title);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id)
    {
        return quizService.getQuestions(id);
    }

    @PostMapping("submit/{id}")      //this id is quiz id
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calcResult(id, responses);
    }

}

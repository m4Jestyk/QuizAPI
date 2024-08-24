package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.QuestionService;
import com.example.demo.model.Question;

import java.util.ArrayList;
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
    public ResponseEntity<List<Question>> allQuestions() {
        try {
            List<Question> questions = questionService.getAllQuestions();
            return ResponseEntity.ok().body(questions);
        } catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable String category)
    {
        List<Question> questions = questionService.getAllQuestionsByCategory(category);

        if(questions.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(questions);
    }

    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question)
    {
        try{
            Question ques = questionService.addQuestion(question);
            return ResponseEntity.status(HttpStatus.CREATED).body(ques);
        } catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id)
    {
        try{
            String s = questionService.deleteQuestionById(id);
            return ResponseEntity.ok().body(s);
        } catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Integer id, @RequestBody Question updates)
    {
        try{
            Question updatedQuestion = questionService.updateQuestionById(id, updates);
            return ResponseEntity.ok().body(updatedQuestion);
        } catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}

package com.example.demo.services;


import com.example.demo.model.Question;
import com.example.demo.model.QuestionWrapper;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.QuizRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.model.Quiz;
import com.example.demo.model.Response;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        Quiz quiz = new Quiz();

        List<Question> questions = questionRepository.findByQuizCategory(numQ, category);


        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);

        return ResponseEntity.ok().body("Quiz created");
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestions(Integer id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> quesFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questions = new ArrayList<>();

        for(Question q : quesFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questions.add(qw);
        }

        return ResponseEntity.ok().body(questions);
    }

    public ResponseEntity<Integer> calcResult(Integer id, List<Response> responses) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Question> questions = quiz.getQuestions();

        int score = 0;
        for(Response r : responses){
            Integer qId = r.getId();
            String res = r.getResponse();

            Question q = questionRepository.findById(qId).get();
            String rightAnswer = q.getRightAnswer();

            if(rightAnswer.equals(res)){
                score++;
            }
        }
        return ResponseEntity.ok().body(score);
    }
}

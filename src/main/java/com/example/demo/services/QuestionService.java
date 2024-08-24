package com.example.demo.services;


import com.example.demo.model.Question;
import com.example.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final  QuestionRepository questionRepository;


    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> getAllQuestionsByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    public Question addQuestion(Question question){
        return questionRepository.save(question);
    }

    public String deleteQuestionById(Integer id)
    {
        questionRepository.deleteById(id);
        return "Question deleted with id:" + id;
    }

    public Question updateQuestionById(Integer id, Question updates) throws Exception
    {
        Optional<Question> question = questionRepository.findById(id);

        if(question.isPresent()){
            Question prevQues = question.get();
            prevQues.setQuestionTitle(updates.getQuestionTitle());
            prevQues.setCategory(updates.getCategory());
            prevQues.setOption1(updates.getOption1());
            prevQues.setOption2(updates.getOption2());
            prevQues.setOption3(updates.getOption3());
            prevQues.setOption4(updates.getOption4());
            prevQues.setDifficultyLevel(updates.getDifficultyLevel());
            prevQues.setRightAnswer(updates.getRightAnswer());
            return questionRepository.save(prevQues);
        }
       else {
           throw new Exception("Question not found");
        }
    }
}

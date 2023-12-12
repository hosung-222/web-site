package com.mysite.hosung.service.questionService;


import com.mysite.hosung.domain.Question;
import java.util.List;

public interface QuestionQueryService {
    List<Question> getQuestionList();

    Question getQuestion(Long id);
}

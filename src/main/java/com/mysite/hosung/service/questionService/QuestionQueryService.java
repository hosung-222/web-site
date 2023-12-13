package com.mysite.hosung.service.questionService;


import com.mysite.hosung.domain.Question;
import java.util.List;
import org.springframework.data.domain.Page;

public interface QuestionQueryService {
    Page<Question> getQuestionList(int page);

    Question getQuestion(Long id);
}

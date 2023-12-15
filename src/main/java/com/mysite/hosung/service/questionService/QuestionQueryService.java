package com.mysite.hosung.service.questionService;


import com.mysite.hosung.domain.Question;
import com.mysite.hosung.domain.User;
import java.util.List;
import org.springframework.data.domain.Page;

public interface QuestionQueryService {
    Page<Question> getQuestionList(int page);

    Question getQuestion(Long id);

    boolean isLiked(Question question, User user);
}

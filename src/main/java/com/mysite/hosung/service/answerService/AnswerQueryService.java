package com.mysite.hosung.service.answerService;

import com.mysite.hosung.domain.Answer;
import com.mysite.hosung.domain.User;

public interface AnswerQueryService {
    Answer getAnswer(Long id);

    boolean isLiked(Answer answer, User user);
}

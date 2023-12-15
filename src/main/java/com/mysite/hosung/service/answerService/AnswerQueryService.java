package com.mysite.hosung.service.answerService;

import com.mysite.hosung.domain.Answer;
import com.mysite.hosung.domain.Question;
import com.mysite.hosung.domain.User;
import java.security.Principal;

public interface AnswerQueryService {
    Answer getAnswer(Long id);

    boolean isLiked(Answer answer, Principal principal);

    Question getQuestion(Long id);
}

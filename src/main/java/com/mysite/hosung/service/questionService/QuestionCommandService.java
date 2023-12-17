package com.mysite.hosung.service.questionService;

import com.mysite.hosung.domain.Question;
import com.mysite.hosung.domain.User;
import com.mysite.hosung.web.dto.QuestionRequestDTO;
import com.mysite.hosung.web.dto.QuestionRequestDTO.QuestionFormDTO;
import java.security.Principal;

public interface QuestionCommandService {

    void create(QuestionRequestDTO.QuestionFormDTO questionFormDTO, Principal principal);
    void modify(Question question, QuestionFormDTO questionFormDTO);
    void delete(Question question);
    void like(Question question, Principal principal);
}

package com.mysite.hosung.service.questionService;

import com.mysite.hosung.web.dto.QuestionRequestDTO;
import java.security.Principal;

public interface QuestionCommandService {

    void create(QuestionRequestDTO.QuestionFormDTO questionFormDTO, Principal principal);
}

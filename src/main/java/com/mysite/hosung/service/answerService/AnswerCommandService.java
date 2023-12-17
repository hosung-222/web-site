package com.mysite.hosung.service.answerService;

import com.mysite.hosung.domain.Answer;
import com.mysite.hosung.web.dto.AnswerRequestDTO;
import com.mysite.hosung.web.dto.AnswerRequestDTO.AnswerFormDTO;
import java.security.Principal;

public interface AnswerCommandService {

    Answer create(AnswerFormDTO answerFormDTO, Long id, Principal principal);

    void modify(Long id, AnswerRequestDTO.AnswerFormDTO answerFormDTO);

    void delete(Long id);

    void like(Answer answer, Principal principal);
}

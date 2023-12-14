package com.mysite.hosung.service.answerService;

import com.mysite.hosung.domain.User;
import com.mysite.hosung.web.dto.AnswerRequestDTO;
import java.security.Principal;

public interface AnswerCommandService {

    void create(AnswerRequestDTO.AnswerFormDTO answerFormDTO, Long id, Principal principal);

    void modify(Long id, AnswerRequestDTO.AnswerFormDTO answerFormDTO);
}

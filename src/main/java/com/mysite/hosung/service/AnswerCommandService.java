package com.mysite.hosung.service;

import com.mysite.hosung.web.dto.AnswerRequestDTO;

public interface AnswerCommandService {

    void create(AnswerRequestDTO.AnswerFormDTO answerFormDTO, Long id);

}

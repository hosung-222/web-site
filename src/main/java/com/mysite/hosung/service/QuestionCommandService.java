package com.mysite.hosung.service;

import com.mysite.hosung.web.dto.QuestionRequestDTO;

public interface QuestionCommandService {

    void create(QuestionRequestDTO.QuestionFormDTO questionFormDTO);
}

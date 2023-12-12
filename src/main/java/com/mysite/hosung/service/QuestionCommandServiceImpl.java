package com.mysite.hosung.service;

import com.mysite.hosung.domain.Question;
import com.mysite.hosung.repository.QuestionRepository;
import com.mysite.hosung.web.dto.QuestionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionCommandServiceImpl implements QuestionCommandService{

    private final QuestionRepository questionRepository;

    @Override
    public void create(QuestionRequestDTO.QuestionFormDTO questionFormDTO) {
        questionRepository.save(Question.builder()
                        .subject(questionFormDTO.getSubject())
                        .content(questionFormDTO.getContent())
                .build());
    }
}

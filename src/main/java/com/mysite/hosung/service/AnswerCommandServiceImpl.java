package com.mysite.hosung.service;

import com.mysite.hosung.domain.Answer;
import com.mysite.hosung.domain.Question;
import com.mysite.hosung.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerCommandServiceImpl implements AnswerCommandService{

    private final QuestionQueryService questionQueryService;
    private final AnswerRepository answerRepository;

    @Override
    public void create(String content, Long id) {
        Question question = questionQueryService.getQuestion(id);
        answerRepository.save(Answer.builder()
                        .question(question)
                        .content(content)
                .build());

    }
}

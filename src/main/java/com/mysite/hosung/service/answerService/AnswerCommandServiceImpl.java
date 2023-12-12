package com.mysite.hosung.service.answerService;

import com.mysite.hosung.domain.Answer;
import com.mysite.hosung.domain.Question;
import com.mysite.hosung.repository.AnswerRepository;
import com.mysite.hosung.service.questionService.QuestionQueryService;
import com.mysite.hosung.web.dto.AnswerRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerCommandServiceImpl implements AnswerCommandService {

    private final QuestionQueryService questionQueryService;
    private final AnswerRepository answerRepository;

    @Override
    public void create(AnswerRequestDTO.AnswerFormDTO answerFormDTO, Long id) {
        Question question = questionQueryService.getQuestion(id);
        answerRepository.save(Answer.builder()
                        .question(question)
                        .content(answerFormDTO.getContent())
                .build());

    }
}

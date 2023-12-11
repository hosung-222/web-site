package com.mysite.hosung.service;

import com.mysite.hosung.domain.Question;
import com.mysite.hosung.repository.QuestionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionQueryServiceImpl implements QuestionQueryService{
    private final QuestionRepository questionRepository;

    @Override
    public List<Question> getQuestionList() {
        return questionRepository.findAll();
    }
}

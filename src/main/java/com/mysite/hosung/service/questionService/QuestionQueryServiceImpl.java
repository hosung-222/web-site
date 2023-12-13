package com.mysite.hosung.service.questionService;

import com.mysite.hosung.apiPayload.DataNotFoundException;
import com.mysite.hosung.domain.Question;
import com.mysite.hosung.repository.QuestionRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionQueryServiceImpl implements QuestionQueryService{
    private final QuestionRepository questionRepository;

    @Override
    public Page<Question> getQuestionList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return questionRepository.findAll(pageable);
    }

    @Override
    public Question getQuestion(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()){
            return question.get();
        }else {
            throw new DataNotFoundException("question not found");
        }

    }
}

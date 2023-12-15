package com.mysite.hosung.service.answerService;

import com.mysite.hosung.apiPayload.DataNotFoundException;
import com.mysite.hosung.domain.Answer;
import com.mysite.hosung.domain.User;
import com.mysite.hosung.domain.mapping.AnswerLike;
import com.mysite.hosung.repository.AnswerLikeRepository;
import com.mysite.hosung.repository.AnswerRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnswerQueryServiceImpl implements AnswerQueryService{
    private final AnswerRepository answerRepository;
    private final AnswerLikeRepository answerLikeRepository;

    @Override
    public Answer getAnswer(Long id) {
        Optional<Answer> answer = answerRepository.findById(id);
        if (answer.isPresent()){
            return answer.get();
        }else {
            throw new DataNotFoundException("answer not found");
        }
    }

    @Override
    public boolean isLiked(Answer answer, User user) {
        Optional<AnswerLike> answerLike = answerLikeRepository.findByAnswerAndUser(answer,user);
        if (answerLike.isEmpty()){
            return true;
        }
        return false;
    }


}

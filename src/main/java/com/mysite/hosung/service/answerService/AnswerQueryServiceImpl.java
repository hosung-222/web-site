package com.mysite.hosung.service.answerService;

import com.mysite.hosung.apiPayload.DataNotFoundException;
import com.mysite.hosung.domain.Answer;
import com.mysite.hosung.domain.Question;
import com.mysite.hosung.domain.User;
import com.mysite.hosung.domain.mapping.AnswerLike;
import com.mysite.hosung.repository.AnswerLikeRepository;
import com.mysite.hosung.repository.AnswerRepository;
import com.mysite.hosung.service.questionService.QuestionQueryService;
import com.mysite.hosung.service.userService.UserQueryService;
import java.security.Principal;
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
    private final QuestionQueryService questionQueryService;
    private final UserQueryService userQueryService;

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
    public boolean isLiked(Answer answer, Principal principal) {
        User user = userQueryService.getUser(principal.getName());
        Optional<AnswerLike> answerLike = answerLikeRepository.findByAnswerAndUser(answer,user);
        if (answerLike.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public Question getQuestion(Long id) {
        return questionQueryService.getQuestion(id);
    }


}

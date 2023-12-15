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
import com.mysite.hosung.web.dto.AnswerRequestDTO;
import java.security.Principal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerCommandServiceImpl implements AnswerCommandService {
    private final AnswerQueryService answerQueryService;
    private final UserQueryService userQueryService;
    private final QuestionQueryService questionQueryService;
    private final AnswerRepository answerRepository;
    private final AnswerLikeRepository answerLikeRepository;


    @Override
    public void create(AnswerRequestDTO.AnswerFormDTO answerFormDTO, Long id, Principal principal) {
        Question question = questionQueryService.getQuestion(id);
        User author = userQueryService.getUser(principal.getName());

        answerRepository.save(Answer.builder()
                        .question(question)
                        .content(answerFormDTO.getContent())
                        .author(author)
                .build());

    }

    @Override
    public void modify(Long id, AnswerRequestDTO.AnswerFormDTO answerFormDTO) {
        Answer answer = answerQueryService.getAnswer(id);
        answer.modify(answerFormDTO.getContent());
        answerRepository.save(answer);
    }

    @Override
    public void delete(Long id) {
        Answer answer = answerQueryService.getAnswer(id);
        answerRepository.delete(answer);
    }

    @Override
    public void like(Answer answer, User user) {
        AnswerLike answerLike = AnswerLike.builder()
                .answer(answer)
                .user(user)
                .build();
        answerLikeRepository.save(answerLike);
    }
}

package com.mysite.hosung.service.answerService;

import com.mysite.hosung.apiPayload.DataNotFoundException;
import com.mysite.hosung.domain.Answer;
import com.mysite.hosung.domain.Question;
import com.mysite.hosung.domain.User;
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

    private final UserQueryService userQueryService;
    private final QuestionQueryService questionQueryService;
    private final AnswerRepository answerRepository;


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
        Optional<Answer> target = answerRepository.findById(id);
        if (target.isPresent()){
            Answer answer = target.get();
            answer.modify(answerFormDTO.getContent());
            answerRepository.save(answer);

        }else {
            throw new DataNotFoundException("answer not found");
        }
    }
}

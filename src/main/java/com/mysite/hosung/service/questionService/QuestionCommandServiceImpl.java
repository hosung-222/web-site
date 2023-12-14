package com.mysite.hosung.service.questionService;

import com.mysite.hosung.domain.Question;
import com.mysite.hosung.domain.User;
import com.mysite.hosung.repository.QuestionRepository;
import com.mysite.hosung.service.userService.UserQueryService;
import com.mysite.hosung.web.dto.QuestionRequestDTO;
import com.mysite.hosung.web.dto.QuestionRequestDTO.QuestionFormDTO;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionCommandServiceImpl implements QuestionCommandService{
    private final UserQueryService userQueryService;
    private final QuestionRepository questionRepository;

    @Override
    public void create(QuestionRequestDTO.QuestionFormDTO questionFormDTO, Principal principal) {
        User author = userQueryService.getUser(principal.getName());

        questionRepository.save(Question.builder()
                        .subject(questionFormDTO.getSubject())
                        .content(questionFormDTO.getContent())
                        .author(author)
                .build());
    }

    @Override
    public void modify(Question question, QuestionFormDTO questionFormDTO) {
        question.modify(questionFormDTO);
        questionRepository.save(question);
    }

    @Override
    public void delete(Question question) {
        questionRepository.delete(question);
    }


}

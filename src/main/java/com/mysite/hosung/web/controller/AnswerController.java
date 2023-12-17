package com.mysite.hosung.web.controller;

import com.mysite.hosung.domain.Answer;
import com.mysite.hosung.domain.User;
import com.mysite.hosung.service.answerService.AnswerCommandService;
import com.mysite.hosung.service.answerService.AnswerQueryService;
import com.mysite.hosung.service.answerService.AnswerQueryServiceImpl;
import com.mysite.hosung.service.questionService.QuestionQueryService;
import com.mysite.hosung.service.userService.UserQueryService;
import com.mysite.hosung.web.dto.AnswerRequestDTO;
import com.mysite.hosung.web.dto.AnswerRequestDTO.AnswerFormDTO;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerCommandService answerCommandService;
    private final AnswerQueryService answerQueryService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Long id,
                               @Valid AnswerRequestDTO.AnswerFormDTO answerFormDTO,
                               BindingResult bindingResult,
                               Principal principal){

        if (bindingResult.hasErrors()){
            model.addAttribute("question", answerQueryService.getQuestion(id));
            return "question_detail";
        }

        Answer answer = answerCommandService.create(answerFormDTO, id, principal);
        return String.format("redirect:/question/detail/%s#answer_%s", id, answer.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String answerModify(AnswerRequestDTO.AnswerFormDTO answerFormDTO, @PathVariable("id")Long id,
                               Principal principal){
        Answer answer = answerQueryService.getAnswer(id);
        if (!answer.getAuthor().getName().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }
        answerFormDTO.setContent(answer.getContent());
        return "answer_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String answerModify(@Valid AnswerRequestDTO.AnswerFormDTO answerFormDTO, BindingResult bindingResult,
                               @PathVariable("id")Long id, Principal principal){
        if (bindingResult.hasErrors()){
            return "answer_form";
        }

        Answer answer = answerQueryService.getAnswer(id);
        if (!answer.getAuthor().getName().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        answerCommandService.modify(id, answerFormDTO);

        return String.format("redirect:/question/detail/%s#answer_%s",
                answer.getQuestion().getId(), answer.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String answerDelete(Principal principal, @PathVariable("id")Long id){
        Answer answer = answerQueryService.getAnswer(id);
        if (!answer.getAuthor().getName().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        answerCommandService.delete(id);
        return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String answerLike(Principal principal, @PathVariable("id")Long id){
        Answer answer = answerQueryService.getAnswer(id);
        answerCommandService.like(answer, principal);
        return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
    }
}

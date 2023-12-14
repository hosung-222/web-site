package com.mysite.hosung.web.controller;

import com.mysite.hosung.domain.User;
import com.mysite.hosung.service.answerService.AnswerCommandService;
import com.mysite.hosung.service.questionService.QuestionQueryService;
import com.mysite.hosung.service.userService.UserQueryService;
import com.mysite.hosung.web.dto.AnswerRequestDTO;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerCommandService answerCommandService;
    private final QuestionQueryService questionQueryService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Long id,
                               @Valid AnswerRequestDTO.AnswerFormDTO answerFormDTO,
                               BindingResult bindingResult,
                               Principal principal){
        if (bindingResult.hasErrors()){
            model.addAttribute("question", questionQueryService.getQuestion(id));
            return "question_detail";
        }

        answerCommandService.create(answerFormDTO, id, principal);
        return String.format("redirect:/question/detail/%s", id);
    }
}

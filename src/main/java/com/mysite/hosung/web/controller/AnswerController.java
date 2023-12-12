package com.mysite.hosung.web.controller;

import com.mysite.hosung.service.AnswerCommandService;
import com.mysite.hosung.service.QuestionQueryService;
import com.mysite.hosung.web.dto.AnswerRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerCommandService answerCommandService;
    private final QuestionQueryService questionQueryService;

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Long id,
                               @Valid AnswerRequestDTO.AnswerFormDTO answerFormDTO,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.addAttribute("question", questionQueryService.getQuestion(id));
            return "question_detail";
        }

        answerCommandService.create(answerFormDTO, id);
        return String.format("redirect:/question/detail/%s", id);
    }
}

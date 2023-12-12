package com.mysite.hosung.web.controller;

import com.mysite.hosung.domain.Question;
import com.mysite.hosung.service.questionService.QuestionCommandService;
import com.mysite.hosung.service.questionService.QuestionQueryService;
import com.mysite.hosung.web.dto.AnswerRequestDTO;
import com.mysite.hosung.web.dto.QuestionRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionQueryService questionQueryService;
    private final QuestionCommandService questionCommandService;

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("questionList", questionQueryService.getQuestionList());
        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id,
                         AnswerRequestDTO.AnswerFormDTO answerFormDTO){
        Question question = questionQueryService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String createQuestion(QuestionRequestDTO.QuestionFormDTO questionFormDTO) {
        return "question_form";
    }

    @PostMapping("/create")
    public String createQuestion(@Valid QuestionRequestDTO.QuestionFormDTO questionFormDTO,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "question_form";
        }
        questionCommandService.create(questionFormDTO);
        return "redirect:/question/list";
    }



}

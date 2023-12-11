package com.mysite.hosung.web.controller;

import com.mysite.hosung.service.QuestionQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionQueryService questionQueryService;

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("questionList", questionQueryService.getQuestionList());
        return "question_list";
    }
}

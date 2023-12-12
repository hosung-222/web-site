package com.mysite.hosung.web.controller;

import com.mysite.hosung.service.AnswerCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerCommandService answerCommandService;

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Long id,
                               @RequestParam(value = "content") String content){
        answerCommandService.create(content, id);
        return String.format("redirect:/question/detail/%s", id);
    }
}

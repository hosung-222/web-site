package com.mysite.hosung.web.controller;

import com.mysite.hosung.domain.Question;
import com.mysite.hosung.domain.User;
import com.mysite.hosung.service.questionService.QuestionCommandService;
import com.mysite.hosung.service.questionService.QuestionQueryService;
import com.mysite.hosung.service.userService.UserQueryService;
import com.mysite.hosung.web.dto.AnswerRequestDTO;
import com.mysite.hosung.web.dto.QuestionRequestDTO;
import com.mysite.hosung.web.dto.QuestionRequestDTO.QuestionFormDTO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionQueryService questionQueryService;
    private final QuestionCommandService questionCommandService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page){
        model.addAttribute("paging", questionQueryService.getQuestionList(page));
        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id,
                         AnswerRequestDTO.AnswerFormDTO answerFormDTO){
        Question question = questionQueryService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String createQuestion(QuestionRequestDTO.QuestionFormDTO questionFormDTO) {
        return "question_form";
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String createQuestion(@Valid QuestionRequestDTO.QuestionFormDTO questionFormDTO,
                                 BindingResult bindingResult,
                                 Principal principal){
        if (bindingResult.hasErrors()){
            return "question_form";
        }
        questionCommandService.create(questionFormDTO, principal);
        return "redirect:/question/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String questionModify(QuestionFormDTO questionFormDTO, @PathVariable("id")Long id, Principal principal){
        Question question = questionQueryService.getQuestion(id);
        if (!question.getAuthor().getName().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }
        questionFormDTO.setContent(question.getContent());
        questionFormDTO.setSubject(question.getSubject());
        return "question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String questionModify(@Valid QuestionFormDTO questionFormDTO, BindingResult bindingResult,
                                 Principal principal, @PathVariable("id")Long id){
        if (bindingResult.hasErrors()){
            return "question_form";
        }
        Question question = questionQueryService.getQuestion(id);
        if (!question.getAuthor().getName().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        questionCommandService.modify(question, questionFormDTO);

        return String.format("redirect:/question/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(Principal principal, @PathVariable("id")Long id){
        Question question = questionQueryService.getQuestion(id);
        if (!question.getAuthor().getName().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        questionCommandService.delete(question);
        return "redirect:/";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String questionLike(Principal principal, @PathVariable("id") Long id){
        Question question = questionQueryService.getQuestion(id);
        questionCommandService.like(question, principal);

        return String.format("redirect:/question/detail/%s", id);
    }

}

package com.mysite.hosung.web.controller;

import com.mysite.hosung.service.userService.UserCommandService;
import com.mysite.hosung.service.userService.UserQueryService;
import com.mysite.hosung.web.dto.UserRequestDTO.UserCreateDTO;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @GetMapping("/signup")
    public String signup(UserCreateDTO userCreateDTO){
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateDTO userCreateDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "signup_form";
        }
        if (!userQueryService.checkPasswordEquals(userCreateDTO)){
            bindingResult.rejectValue("password2", "passwordIncorrect", "패스워드가 일치하지 않습니다.");
            return "signup_form";
        }

        try {
            userCommandService.create(userCreateDTO);
        }catch (DataIntegrityViolationException e){ // 중복일 경우 예외 처리
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자 입니다.");
            return "signup_form";
        }catch (Exception e){
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "login_form";
    }

}

package com.mysite.hosung.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerRequestDTO {

    @Getter
    @Setter
    public static class AnswerFormDTO{

        @NotEmpty(message = "내용은 필수항목입니다.")
        private String content;
    }
}

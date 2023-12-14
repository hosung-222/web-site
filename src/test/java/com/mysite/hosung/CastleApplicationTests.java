package com.mysite.hosung;

import com.mysite.hosung.service.questionService.QuestionCommandService;
import com.mysite.hosung.web.dto.QuestionRequestDTO;
import java.security.Principal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CastleApplicationTests {

	@Autowired
	private QuestionCommandService questionCommandService;

	@Test
	void contextLoads() {
	}

	@DisplayName("더미 데이터 삽입")
	@Test
	void testJpa(){
		for (int i = 0; i <= 300 ; i++) {
			String suject = String.format("테스트 데이터 입니다 : [%03d]",i);
			String content = "내용";
			QuestionRequestDTO.QuestionFormDTO questionFormDTO = QuestionRequestDTO.QuestionFormDTO.builder()
					.subject(suject)
					.content(content)
					.build();
			questionCommandService.create(questionFormDTO, null);
		}
	}

}

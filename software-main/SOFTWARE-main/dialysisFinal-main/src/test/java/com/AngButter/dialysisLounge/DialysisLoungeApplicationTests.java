package com.AngButter.dialysisLounge;

import com.AngButter.dialysisLounge.Answer.Answer;
import com.AngButter.dialysisLounge.Answer.AnswerRepository;
import com.AngButter.dialysisLounge.Question.Question;
import com.AngButter.dialysisLounge.Question.QuestionRepository;
import com.AngButter.dialysisLounge.Question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DialysisLoungeApplicationTests {

	@Autowired
	private QuestionService questionService;

	@Test
	void testJpa() {
		for (int i = 1; i <= 300; i++) {
			String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "내용 없음 ";
			this.questionService.create(subject, content, null);
		}




//		Question q1 = new Question();
//		q1.setSubject("양재동에 투석병원이 있나요?");
//		q1.setContent("양재동에 투석병원이 있는지 알고싶습니다.");
//		q1.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q1);
//
//		Question q2 = new Question();
//		q2.setSubject("투석환자 식단 질문입니다.");
//		q2.setContent("어떤 음식을 조심해야되나요?");
//		q2.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q2);
	}

}

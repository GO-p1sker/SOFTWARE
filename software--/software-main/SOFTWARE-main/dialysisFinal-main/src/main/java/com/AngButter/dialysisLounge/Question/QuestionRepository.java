package com.AngButter.dialysisLounge.Question;

import com.AngButter.dialysisLounge.Question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject, String content);
    List<Question> findBySubjectLike(String subject);
    //페이징 구현을 위해 추가로 설치하는 라이브러리
    Page<Question> findAll(Pageable pageable);

    //DB에서 조회한 결과 페이지화한다.
    Page<Question> findAll(Specification<Question> spec, Pageable pageable);
}

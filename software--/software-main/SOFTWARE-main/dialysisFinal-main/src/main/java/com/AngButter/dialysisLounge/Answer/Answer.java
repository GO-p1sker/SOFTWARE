package com.AngButter.dialysisLounge.Answer;

import com.AngButter.dialysisLounge.Question.Question;
import com.AngButter.dialysisLounge.User.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(columnDefinition = "TEXT")
    private String content; // 내용

    @CreatedDate
    private LocalDateTime createDate; //답변 데이터 작성한 일시

    @ManyToOne //N:1관계를 나타냄
    private Question question; //질문데이터

    //로그인 기능 시 사용
    @ManyToOne
    private SiteUser author; //작성자

    private LocalDateTime modifyDate; //수정일시
}

package com.AngButter.dialysisLounge.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String customerPhone;

    @Column(unique = true)
    private String customerAddress;

    // UserRole 필드 추가
    @Enumerated(EnumType.STRING) // Enum 값을 문자열로 저장
    private UserRole role = UserRole.USER; // 기본값을 USER로 설정
}

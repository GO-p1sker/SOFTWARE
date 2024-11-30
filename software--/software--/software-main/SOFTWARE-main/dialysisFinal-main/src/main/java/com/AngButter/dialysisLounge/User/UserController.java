package com.AngButter.dialysisLounge.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // 회원가입 폼 화면
    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {
        return "signup_form";
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        // 폼 유효성 검사 실패 시
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        // 비밀번호 확인 검증
        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "비밀번호가 일치하지 않습니다.");
            return "signup_form";
        }

        try {
            // UserService에 회원 정보 생성 요청
            userService.create(
                    userCreateForm.getUsername(),
                    userCreateForm.getEmail(),
                    userCreateForm.getPassword1(),
                    userCreateForm.getCustomerPhone(),
                    userCreateForm.getCustomerAddress()
            );
        } catch (DataIntegrityViolationException e) { // 중복 데이터 처리
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 계정입니다.");
            return "signup_form";
        } catch (Exception e) { // 기타 예외 처리
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }

        return "redirect:/question/list"; // 성공 시 리다이렉트
    }

    // 로그인 폼 화면
    @GetMapping("/login")
    public String login() {
        return "login_form";
    }
}

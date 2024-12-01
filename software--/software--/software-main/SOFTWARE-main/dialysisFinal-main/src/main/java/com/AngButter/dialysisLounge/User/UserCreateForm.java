package com.AngButter.dialysisLounge.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {

        @Size(min = 3, max = 25)
        @NotEmpty(message = "ID를 입력해주세요.")
        private String username;

        @NotEmpty(message = "비밀번호를 입력해주세요.")
        private String password1;

        @NotEmpty(message = "비밀번호를 한번 더 입력해주세요.")
        private String password2;

        @NotEmpty(message = "이메일을 입력해주세요.")
        @Email
        private String email;

        @NotEmpty(message = "전화번호를 입력해주세요.")
        private String customerPhone;

        @NotEmpty(message = "주소를 입력해주세요.")
        private String customerAddress;
}

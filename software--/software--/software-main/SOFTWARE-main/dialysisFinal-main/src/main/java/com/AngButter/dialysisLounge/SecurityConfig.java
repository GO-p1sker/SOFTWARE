package com.AngButter.dialysisLounge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        // 메인화면, 로그인, 로그아웃 경로 허용
                        .requestMatchers("/static/**", "/**.css", "/**.js", "/**.png", "/images/**", "/**.jpeg").permitAll()
                        .requestMatchers("/user/login", "/user/logout", "/main", "/user/signup").permitAll()
                        // 그 외 모든 요청 인증 필요
                        .anyRequest().authenticated())
                .csrf((csrf) -> csrf.disable())
                .headers((headers) -> headers
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
                // 로그인 설정
                .formLogin((formLogin) -> formLogin
                        .loginPage("/user/login") // 로그인 폼 URL
                        .successHandler((request, response, authentication) -> {
                            response.sendRedirect("/main"); // 로그인 성공 시 /main으로 리다이렉트
                        })
                        .permitAll())
                // 로그아웃 설정
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) // 로그아웃 URL
                        .logoutSuccessUrl("/main") // 로그아웃 성공 시 이동 URL
                        .invalidateHttpSession(true)) // 사용자 세션 삭제
        ;
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}

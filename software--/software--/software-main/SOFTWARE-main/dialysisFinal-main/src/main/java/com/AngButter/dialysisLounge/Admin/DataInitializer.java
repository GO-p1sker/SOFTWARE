package com.AngButter.dialysisLounge.Admin;

import com.AngButter.dialysisLounge.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class DataInitializer {

    private final UserService userService;

    @PostConstruct
    public void init() {
        if (userService.findUser("admin").isEmpty()) {
            userService.createAdmin("admin", "admin@example.com", "admin123");
            System.out.println("Admin account created: username=admin, password=admin123");
        }
    }
}

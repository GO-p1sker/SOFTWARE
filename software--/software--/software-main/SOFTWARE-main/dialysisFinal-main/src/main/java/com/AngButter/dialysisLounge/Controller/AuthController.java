package com.AngButter.dialysisLounge.Controller;


import com.AngButter.dialysisLounge.User.SiteUser;
import com.AngButter.dialysisLounge.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;

    @GetMapping("/auth/status")
    public Map<String, Object> checkAuthStatus() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> response = new HashMap<>();

        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())) {
            SiteUser user = userService.findUser(auth.getName())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            response.put("isAuthenticated", true);
            response.put("isAdmin", user.getRole().name().equals("ADMIN"));
        } else {
            response.put("isAuthenticated", false);
            response.put("isAdmin", false);
        }

        return response;
    }
}
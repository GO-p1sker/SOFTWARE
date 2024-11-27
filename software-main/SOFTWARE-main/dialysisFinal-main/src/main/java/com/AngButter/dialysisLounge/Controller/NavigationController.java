package com.AngButter.dialysisLounge.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NavigationController {

    @GetMapping("/go-to-url")
    public String goToUrl() {
        // 해당 URL로 리다이렉트
        return "redirect:https://martin-pleased-preferably.ngrok-free.app";
    }
}

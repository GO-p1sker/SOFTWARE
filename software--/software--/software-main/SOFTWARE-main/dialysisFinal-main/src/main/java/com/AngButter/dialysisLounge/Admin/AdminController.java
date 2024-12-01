package com.AngButter.dialysisLounge.Admin;

import com.AngButter.dialysisLounge.User.SiteUser;
import com.AngButter.dialysisLounge.User.UserRole;
import com.AngButter.dialysisLounge.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')") // 관리자는 ROLE_ADMIN 권한 필요
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    // 모든 사용자 조회
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<SiteUser> users = adminService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user_list"; // admin 패키지에 위치한 뷰
    }

    // 특정 사용자 삭제
    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        adminService.deleteUserById(id);
        return "redirect:/admin/users";
    }

    // 사용자 권한 변경
    @PostMapping("/users/{id}/role")
    public String updateUserRole(@PathVariable Long id, @RequestParam String role) {
        adminService.updateUserRole(id, role);
        return "redirect:/admin/users";
    }
    @PostMapping("/create-admin")
    public String createAdmin(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        userService.createAdmin(username, email, password);
        return "관리자 계정이 생성되었습니다.";
    }
}

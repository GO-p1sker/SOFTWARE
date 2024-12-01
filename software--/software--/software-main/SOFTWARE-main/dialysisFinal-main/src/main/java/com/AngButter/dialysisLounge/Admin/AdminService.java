package com.AngButter.dialysisLounge.Admin;

import com.AngButter.dialysisLounge.DataNotFoundException;
import com.AngButter.dialysisLounge.User.SiteUser;
import com.AngButter.dialysisLounge.User.UserRepository;
import com.AngButter.dialysisLounge.User.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final UserRepository userRepository;

    public List<SiteUser> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUserRole(Long id, String role) {
        SiteUser user = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found"));
        if ("ADMIN".equalsIgnoreCase(role)) {
            user.setRole(UserRole.ADMIN);
        } else {
            user.setRole(UserRole.USER);
        }
        userRepository.save(user);
    }
}

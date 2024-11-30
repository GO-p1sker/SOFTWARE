package com.AngButter.dialysisLounge.User;

import com.AngButter.dialysisLounge.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String email, String password, String customerPhone, String customerAddress) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setCustomerPhone(customerPhone);
        user.setCustomerAddress(customerAddress);
        user.setRole(UserRole.USER); // 기본 USER 권한 설정
        this.userRepository.save(user);
        return user;
    }

    public SiteUser createAdmin(String username, String email, String password) {
        SiteUser admin = new SiteUser();
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setPassword(passwordEncoder.encode(password));
        admin.setRole(UserRole.ADMIN); // ADMIN 권한 설정
        this.userRepository.save(admin);
        return admin;
    }

    public SiteUser getUser(String username) {
        return this.userRepository.findByusername(username)
                .orElseThrow(() -> new DataNotFoundException("siteuser not found"));
    }

    public Optional<SiteUser> findUser(String username) {
        return this.userRepository.findByusername(username);
    }
}

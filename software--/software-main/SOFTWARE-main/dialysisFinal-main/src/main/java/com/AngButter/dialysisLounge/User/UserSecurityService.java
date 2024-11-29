package com.AngButter.dialysisLounge.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    //사용자명(username)으로 사용자(User)객체 조회 후 리턴하는 메서드
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SiteUser> _siteUser = this.userRepository.findByusername(username);
        if (_siteUser.isEmpty()) { //해당 사용자명 데이터가 없을때 발생하는 에러
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        SiteUser siteUser = _siteUser.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue())); //admin 경우 권한 부여
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue())); // 그 외에는 user 권한 부여
        }
        return new User(siteUser.getUsername(), siteUser.getPassword(), authorities); //User 객체의 이름, 비밀번호, 권한리스트 전달
    }
}


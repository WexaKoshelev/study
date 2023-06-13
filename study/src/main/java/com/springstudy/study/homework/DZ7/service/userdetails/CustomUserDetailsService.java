package com.springstudy.study.homework.DZ7.service.userdetails;

import com.springstudy.study.homework.DZ7.model.Users;
import com.springstudy.study.homework.DZ7.constants.UserRolesConstants;
import com.springstudy.study.homework.DZ7.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomUserDetailsService
        implements UserDetailsService {
    private final UserRepository userRepository;

    @Value("${spring.security.user.name}")
    private String adminUserName;
    @Value("${spring.security.user.password}")
    private String adminPassword;


    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(adminUserName)) {
            return new CustomUserDetails(null, username, adminPassword, List.of(new SimpleGrantedAuthority("ROLE_" + UserRolesConstants.ADMIN)));
        } else {
            Users user = userRepository.findUserByLogin(username);
            List<GrantedAuthority> authorities = new ArrayList<>();

            //ROLE_USER, ROLE_LIBRARIAN
            authorities.add(new SimpleGrantedAuthority(user.getRole().getId() == 1L
                    ? "ROLE_" + UserRolesConstants.USER
                    : "ROLE_" + UserRolesConstants.LIBRARIAN));

            return new CustomUserDetails(user.getId().intValue(), username, user.getPassword(), authorities);
        }
    }
}


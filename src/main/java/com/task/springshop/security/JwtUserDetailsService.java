package com.task.springshop.security;

import com.task.springshop.entity.User;
import com.task.springshop.entity.UserStatus;
import com.task.springshop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userService.findUserByLogin(login);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User with username: " + login + " not found");
        }

        User presentUser = user.get();
        JwtUserDetails jwtUserDetails = new JwtUserDetails(
                presentUser.getLogin(),
                presentUser.getPassword(),
                presentUser.getUserStatus().equals(UserStatus.ACTIVE),
                List.of(new SimpleGrantedAuthority("ROLE_" + presentUser.getRole().name())));

        log.info("In loadUserByUsername - user with username: {} successfully loaded", login);
        return jwtUserDetails;
    }
}

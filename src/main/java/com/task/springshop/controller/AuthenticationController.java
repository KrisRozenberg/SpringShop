package com.task.springshop.controller;

import com.task.springshop.entity.User;
import com.task.springshop.security.JwtTokenProvider;
import com.task.springshop.service.UserService;
import com.task.springshop.util.AuthenticationRequestDto;
import com.task.springshop.util.RefreshTokenDto;
import com.task.springshop.util.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserInfo> login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            Optional<User> user = userService.findUserByLogin(username);

            if (user.isEmpty()) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String access_token = jwtTokenProvider.createAccessToken(username, List.of(user.get().getRole()));
            String refresh_token = jwtTokenProvider.createRefreshToken(username, List.of(user.get().getRole()));

            UserInfo userInfo = new UserInfo(username, user.get().getRole(), access_token, refresh_token);

            return new ResponseEntity<>(userInfo, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping("/refreshToken")
    public ResponseEntity<RefreshTokenDto> refreshToken(HttpServletRequest request) {
        Map<Object, Object> response = new HashMap<>();
        RefreshTokenDto refreshTokenDto = new RefreshTokenDto();
        try {
            String token = jwtTokenProvider.resolveToken(request);

            if (token != null && jwtTokenProvider.validateToken(token)) {
                String username = jwtTokenProvider.getUsername(token);
                Optional<User> user = userService.findUserByLogin(username);
                if (user.isEmpty()) {
                    throw new UsernameNotFoundException("User with username: " + username + " not found");
                }

                String access_token = jwtTokenProvider.createAccessToken(username, List.of(user.get().getRole()));
                String refresh_token = jwtTokenProvider.createRefreshToken(username, List.of(user.get().getRole()));

                refreshTokenDto.setAccess_token(access_token);
                refreshTokenDto.setRefresh_token(refresh_token);


            }
            return new ResponseEntity<>(refreshTokenDto, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}

package com.mahendracandi.poselectricityapp.services;

import com.mahendracandi.poselectricityapp.dtos.LoginInfoDto;
import com.mahendracandi.poselectricityapp.dtos.LoginRequestDto;
import com.mahendracandi.poselectricityapp.dtos.LogoutRequestDto;
import com.mahendracandi.poselectricityapp.entities.User;
import com.mahendracandi.poselectricityapp.entities.UserLogin;
import com.mahendracandi.poselectricityapp.repositories.UserRepository;
import com.mahendracandi.poselectricityapp.utils.PasswordUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class LoginService {
    public static final short LOGIN_STATUS = (short) 1;
    public static final short LOGOUT_STATUS = (short) 0;

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginInfoDto login(LoginRequestDto requestDto) {
        final var user = userRepository.findUserByUsername(requestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));

        final var isPasswordVerified = PasswordUtil.verifyPassword(requestDto.getPassword(), user.getPassword());
        if (!isPasswordVerified) {
            throw new IllegalArgumentException("Login invalid!");
        }

        UserLogin userLogin;
        if (Objects.nonNull(user.getUserLogin())) {
            userLogin = user.getUserLogin();
            userLogin.setIsLogin(LOGIN_STATUS);
            userLogin.setTimestamp(LocalDateTime.now());
        } else {
            userLogin = UserLogin.builder()
                    .user(user)
                    .userId(user.getUserId())
                    .isLogin(LOGIN_STATUS)
                    .timestamp(LocalDateTime.now())
                    .build();
        }
        user.setUserLogin(userLogin);
        userRepository.save(user);
        return buildLoginInfoDto(user, userLogin);
    }

    public LoginInfoDto logout(LogoutRequestDto requestDto) {
        final var user = userRepository.findUserByUsername(requestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));

        UserLogin userLogin;
        if (Objects.nonNull(user.getUserLogin())) {
            userLogin = user.getUserLogin();
            userLogin.setIsLogin(LOGOUT_STATUS);
        } else {
            userLogin = UserLogin.builder()
                    .user(user)
                    .userId(user.getUserId())
                    .isLogin(LOGOUT_STATUS)
                    .build();
        }
        user.setUserLogin(userLogin);
        userRepository.save(user);
        return buildLoginInfoDto(user, userLogin);
    }

    private static LoginInfoDto buildLoginInfoDto(User user, UserLogin userLogin) {
        return LoginInfoDto.builder()
                .username(user.getUsername())
                .isLogin(userLogin.getIsLogin())
                .lastLogin(userLogin.getTimestamp())
                .hakAksesValue(user.getHakAkses().getValue())
                .build();
    }
}

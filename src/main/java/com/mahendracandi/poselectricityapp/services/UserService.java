package com.mahendracandi.poselectricityapp.services;

import com.mahendracandi.poselectricityapp.dtos.UserRequestDto;
import com.mahendracandi.poselectricityapp.entities.User;
import com.mahendracandi.poselectricityapp.enums.HakAkses;
import com.mahendracandi.poselectricityapp.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String username, String password, Integer hakAkses) {
        return userRepository.save(User.builder()
                .username(username)
                .password(hashPassword(password))
                .hakAkses(HakAkses.findByValue(hakAkses))
                .createdDate(LocalDateTime.now())
                .build());
    }

    public List<User> findUsers(String username) {
        if (StringUtils.isBlank(username)) {
            return userRepository.findAll();
        }
        return userRepository.findAllByUsernameStartsWith(username);
    }

    public User updateUser(String username, UserRequestDto requestDto) {
        var user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found")); // todo implement better exception

        if (Objects.nonNull(requestDto.getUsername())) {
            user.setUsername(requestDto.getUsername());
        }

        if (Objects.nonNull(requestDto.getPassword())) {
            user.setPassword(hashPassword(requestDto.getPassword()));
        }

        if (Objects.nonNull(requestDto.getHakAkses())) {
            user.setHakAkses(HakAkses.findByValue(requestDto.getHakAkses()));
        }

        return userRepository.save(user);
    }

    public void deleteUser(String username) {
        var user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found")); // todo implement better exception

        userRepository.delete(user);
    }

    private static String hashPassword(String password) {
        final var secureRandom = new SecureRandom();
        final var salt = new byte[16];
        secureRandom.nextBytes(salt);

        final var spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        final SecretKeyFactory factory;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return Arrays.toString(factory.generateSecret(spec).getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalArgumentException(e.getMessage(), e); // todo implement custom exception
        }
    }
}

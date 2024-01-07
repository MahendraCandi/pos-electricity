package com.mahendracandi.poselectricityapp.services;

import com.mahendracandi.poselectricityapp.entities.User;
import com.mahendracandi.poselectricityapp.enums.HakAkses;
import com.mahendracandi.poselectricityapp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String username, String password, Integer hakAkses) {
        return userRepository.save(User.builder()
                .username(username) // todo make username unique
                .password(hashPassword(password))
                .hakAkses(HakAkses.findByValue(hakAkses))
                .createdDate(LocalDateTime.now())
                .build());
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

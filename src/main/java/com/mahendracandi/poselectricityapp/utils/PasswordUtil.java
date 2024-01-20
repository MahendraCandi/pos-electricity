package com.mahendracandi.poselectricityapp.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Objects;

public class PasswordUtil {
    private PasswordUtil() {
        // All method should be static
    }

    private static final String PASSWORD_DELIMITER = "::";

    public static String hashPassword(String password) {
        final var salt = getSalt();
        String hashedPassword;
        String encodedSalt;
        try {
            hashedPassword = getHashedPassword(password, salt);
            encodedSalt = base64Encode(salt);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalArgumentException(e.getMessage(), e); // todo implement custom exception
        }

        return hashedPassword + PASSWORD_DELIMITER + encodedSalt;
    }

    public static boolean verifyPassword(String password, String storedPassword) {
        final var split = storedPassword.split(PASSWORD_DELIMITER);
        final var hashedStoredPassword = split[0];
        final var encodedSalt = split[1];

        final String hashedPassword;
        try {
            hashedPassword = getHashedPassword(password, base64Decode(encodedSalt));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalArgumentException(e.getMessage(), e); // todo implement custom exception
        }

        return Objects.equals(hashedStoredPassword, hashedPassword);
    }

    private static byte[] getSalt() {
        final var secureRandom = new SecureRandom();
        final var salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    private static String getHashedPassword(String rawPassword, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final var spec = new PBEKeySpec(rawPassword.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return base64Encode(hash);
    }

    private static String base64Encode(byte[] src) {
        return Base64.getEncoder().encodeToString(src);
    }

    private static byte[] base64Decode(String src) {
        return Base64.getDecoder().decode(src);
    }
}

package com.circle_vibe_core.app.shared.service;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import com.circle_vibe_core.app.user.entity.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class HashService {
    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789?#_-";
    private static final RandomGenerator RANDOM = RandomGenerator.getDefault();

    public static String generate(int length) {
        return IntStream.range(0, length)
                .mapToObj(i -> String.valueOf(CHARS.charAt(RANDOM.nextInt(CHARS.length()))))
                .collect(Collectors.joining());
    }

    public String generateHash(String params) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(params.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String decrypt(String rawString) {
        Argon2PasswordEncoder encoder = this.getEncoder();
        return encoder.encode(rawString);
    }

    public Boolean compare(String raw, String hash) {
        Argon2PasswordEncoder encoder = this.getEncoder();

        return encoder.matches(raw, hash);
    }

    private Argon2PasswordEncoder getEncoder() {
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(
            16, 32, 1, 65536, 3);
        return encoder;
    }
}

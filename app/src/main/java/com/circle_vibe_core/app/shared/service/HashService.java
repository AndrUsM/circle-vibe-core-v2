package com.circle_vibe_core.app.shared.service;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import com.circle_vibe_core.app.config.Argon2Properties;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@EnableConfigurationProperties(Argon2Properties.class)
public class HashService {
    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789?#_-";
    private static final RandomGenerator RANDOM = RandomGenerator.getDefault();
	private final Argon2PasswordEncoder encoder;

	public HashService(Argon2Properties properties) {
		this.encoder = this.getEncoder(properties);
	}

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
        return this.encoder.encode(rawString);
    }

    public Boolean compare(String raw, String hash) {
        return this.encoder.matches(raw, hash);
    }

	private Argon2PasswordEncoder getEncoder(Argon2Properties properties) {
		return new Argon2PasswordEncoder(
            properties.saltLength(),
            properties.hashLength(),
            properties.parallelism(),
            properties.memory(),
            properties.iterations()
        );
	}
}

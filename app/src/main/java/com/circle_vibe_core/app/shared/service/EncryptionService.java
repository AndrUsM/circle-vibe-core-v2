
package com.circle_vibe_core.app.shared.service;

import com.circle_vibe_core.app.config.EncryptionConstants;
import com.circle_vibe_core.app.config.EncryptionProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@Service
@EnableConfigurationProperties(EncryptionProperties.class)
public class EncryptionService {

    private final SecretKey secretKey;
    private final SecureRandom random = new SecureRandom();

    public EncryptionService(EncryptionProperties properties) {
        byte[] key = Base64.getDecoder().decode(properties.secretKey());

        if (key.length != EncryptionConstants.KEY_LENGTH)
            throw new IllegalArgumentException("Invalid AES key length");

        this.secretKey = new SecretKeySpec(key, "AES");
    }

    public String encrypt(String value) {
        try {
            byte[] iv = new byte[EncryptionConstants.IV_LENGTH];
            random.nextBytes(iv);

            byte[] encrypted = cipher(Cipher.ENCRYPT_MODE, iv)
                    .doFinal(value.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(
                    ByteBuffer.allocate(iv.length + encrypted.length)
                            .put(iv)
                            .put(encrypted)
                            .array()
            );

        } catch (Exception e) {
            throw new IllegalStateException("Encryption failed", e);
        }
    }

    public String decrypt(String value) {
        try {
            byte[] data = Base64.getDecoder().decode(value);

            if (data.length < EncryptionConstants.IV_LENGTH + 16)
                throw new IllegalArgumentException("Invalid encrypted data");

            ByteBuffer buffer = ByteBuffer.wrap(data);

            byte[] iv = new byte[EncryptionConstants.IV_LENGTH];
            buffer.get(iv);

            byte[] encrypted = new byte[buffer.remaining()];
            buffer.get(encrypted);

            return new String(
                    cipher(Cipher.DECRYPT_MODE, iv).doFinal(encrypted),
                    StandardCharsets.UTF_8
            );

        } catch (Exception e) {
            throw new IllegalStateException("Decryption failed", e);
        }
    }

    private Cipher cipher(int mode, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance(EncryptionConstants.ALGORITHM);

        cipher.init(
                mode,
                secretKey,
                new GCMParameterSpec(EncryptionConstants.TAG_LENGTH, iv)
        );

        return cipher;
    }
}

package com.verygood.security.coding.util;

import com.verygood.security.coding.exception.CipherDecryptException;
import com.verygood.security.coding.exception.CipherEncryptException;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.requireNonNull;

@Slf4j
public enum CipherUtils {
    ;
    private static final String DEFAULT_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static SecretKeySpec secretKey;

    public static String encrypt(String strToEncrypt, String secret) {
        return encrypt(strToEncrypt, secret, DEFAULT_ALGORITHM);
    }

    public static String encrypt(String whatToEncrypt, String secret, String algorithm) throws CipherEncryptException {
        requireNonNull(whatToEncrypt);
        requireNonNull(secret);
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(secret));
            return Base64.getEncoder().encodeToString(cipher.doFinal(whatToEncrypt.getBytes(UTF_8)));
        } catch (Exception e) {
            log.error("Error encrypting: " + e);
            throw new CipherEncryptException(e);
        }
    }

    public static String decrypt(String strToEncrypt, String secret) {
        return decrypt(strToEncrypt, secret, DEFAULT_ALGORITHM);
    }

    public static String decrypt(String whatToDecrypt, String secret, String algorithm) throws CipherDecryptException {
        requireNonNull(whatToDecrypt);
        requireNonNull(secret);
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(secret));
            return new String(cipher.doFinal(Base64.getDecoder().decode(whatToDecrypt)));
        } catch (Exception e) {
            log.error("Error decrypting: " + e);
            throw new CipherDecryptException(e);
        }
    }


    private static SecretKeySpec getSecretKey(String myKey) {
        try {
            byte[] key = myKey.getBytes(UTF_8);
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            return new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            log.error("Exception setting key", e);
            throw new IllegalStateException("Failed to create SecretKeySpec", e);
        }
    }
}

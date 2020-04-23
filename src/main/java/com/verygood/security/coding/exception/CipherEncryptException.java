package com.verygood.security.coding.exception;

public class CipherEncryptException extends RuntimeException {
    public CipherEncryptException(String message) {
        super(message);
    }

    public CipherEncryptException(String message, Throwable cause) {
        super(message, cause);
    }

    public CipherEncryptException(Throwable cause) {
        super(cause);
    }
}

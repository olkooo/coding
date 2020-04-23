package com.verygood.security.coding.exception;

public class CipherDecryptException extends RuntimeException {
    public CipherDecryptException(String message) {
        super(message);
    }

    public CipherDecryptException(String message, Throwable cause) {
        super(message, cause);
    }

    public CipherDecryptException(Throwable cause) {
        super(cause);
    }
}
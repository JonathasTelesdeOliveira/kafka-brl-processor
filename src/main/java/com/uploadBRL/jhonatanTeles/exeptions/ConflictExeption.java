package com.uploadBRL.jhonatanTeles.exeptions;

public class ConflictExeption extends RuntimeException {
    public ConflictExeption(String message) {
        super(message);
    }
    public ConflictExeption(String message, Throwable cause) {
        super(message, cause);
    }
}

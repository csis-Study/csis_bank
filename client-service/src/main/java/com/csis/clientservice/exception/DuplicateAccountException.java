package com.csis.clientservice.exception;

// 账号冲突异常
public class DuplicateAccountException extends RuntimeException {
    public DuplicateAccountException(String message) {
        super(message);
    }
}
package com.bfh.ticket.exception;

public class CtiException extends RuntimeException {
    private CtiException(String message) {
        super(message);
    }
}

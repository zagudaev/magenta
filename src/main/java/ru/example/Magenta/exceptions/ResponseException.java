package ru.example.Magenta.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResponseException extends ResponseStatusException {
    private String message;

    public ResponseException(HttpStatus status, String message) {
        super(status, message);
        this.message = message;
    }
}

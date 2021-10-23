package ru.example.magenta.exceptions;

public class FileParseException extends RuntimeException {
    public FileParseException(Throwable cause) {
        super(cause);
    }
}

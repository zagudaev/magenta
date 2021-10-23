package ru.example.magenta.exceptions;

public class DistanceNotFoundException extends RuntimeException {
    public DistanceNotFoundException(Long id) {
        super("Направление с таким ID не найдено : " + id);
    }
}

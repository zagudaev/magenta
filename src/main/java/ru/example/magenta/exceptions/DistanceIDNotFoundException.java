package ru.example.magenta.exceptions;

public class DistanceIDNotFoundException extends RuntimeException{
    public DistanceIDNotFoundException(Long id) {
        super("Направление с таким ID не найдено : "+ id);
    }
}

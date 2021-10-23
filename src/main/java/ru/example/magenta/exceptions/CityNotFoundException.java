package ru.example.magenta.exceptions;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(long id) {
        super("Город с таким ID не найден :" + id);
    }
}

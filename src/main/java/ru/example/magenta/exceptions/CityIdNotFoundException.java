package ru.example.magenta.exceptions;

public class CityIdNotFoundException extends RuntimeException{
    public CityIdNotFoundException(long id) {
        super("Город с таким ID не найден :" + id);
    }
}

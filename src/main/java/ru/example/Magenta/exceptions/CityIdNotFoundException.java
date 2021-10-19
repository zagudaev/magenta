package ru.example.Magenta.exceptions;

public class CityIdNotFoundException extends RuntimeException{
    public CityIdNotFoundException(long id) {
        super("Город с таким ID не найден :" + id);
    }
}

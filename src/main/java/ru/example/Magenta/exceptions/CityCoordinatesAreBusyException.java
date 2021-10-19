package ru.example.Magenta.exceptions;

import ru.example.Magenta.DTO.CityDTO;

public class CityCoordinatesAreBusyException extends RuntimeException{
    public CityCoordinatesAreBusyException(CityDTO cityDTO) {
        super("Данные координаты уже заняты  : " + cityDTO.getLatitude() + ":" +
                cityDTO.getLongitude());
    }
}

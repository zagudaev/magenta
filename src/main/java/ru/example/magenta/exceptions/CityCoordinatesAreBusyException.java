package ru.example.magenta.exceptions;

import ru.example.magenta.dto.CityDTO;

public class CityCoordinatesAreBusyException extends RuntimeException{
    public CityCoordinatesAreBusyException(CityDTO cityDTO) {
        super("Данные координаты уже заняты  : " + cityDTO.getLatitude() + ":" +
                cityDTO.getLongitude());
    }
}

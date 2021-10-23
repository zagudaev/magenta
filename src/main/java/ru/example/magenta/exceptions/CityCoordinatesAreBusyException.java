package ru.example.magenta.exceptions;

import ru.example.magenta.dto.CityDto;

public class CityCoordinatesAreBusyException extends RuntimeException {
    public CityCoordinatesAreBusyException(CityDto cityDTO) {
        super("Данные координаты уже заняты  : " + cityDTO.getLatitude() + ":" +
                cityDTO.getLongitude());
    }
}

package ru.example.magenta.exceptions;

import ru.example.magenta.dto.CityDTO;
import ru.example.magenta.dto.DistanceDTO;

public class DistanceIsBusyException extends RuntimeException{
    public DistanceIsBusyException(DistanceDTO distanceDTO) {
        super("данное направление занято : " + distanceDTO.getFromCity() + ":" +
                distanceDTO.getToCity());
    }
    public DistanceIsBusyException(CityDTO fromCity,CityDTO toCity) {
        super("данное направление занято : " + fromCity.getName() + ":" +
                toCity.getName());
    }
}

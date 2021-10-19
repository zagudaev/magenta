package ru.example.Magenta.exceptions;

import ru.example.Magenta.DTO.CityDTO;
import ru.example.Magenta.DTO.DistanceDTO;

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
